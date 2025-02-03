package com.service;

import com.dto.request.ChangePasswordRequest;
import com.dto.response.ApiResponse;
import com.entity.OTPEntity;
import com.entity.UserEntity;
import com.exception.custom.NotFoundException;
import com.exception.custom.UserException;
import com.repository.OTPRepository;
import com.repository.UserRepository;
import com.util.BuildResponse;
import com.util.enums.AccountTypeEnum;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OTPService {

    private static final String CHARACTERS = "0123456789";
    private static final int OTP_LENGTH = 6;
    private final SecureRandom secureRandom = new SecureRandom();
    private final OTPRepository otpRepository;
    private final EmailSenderService emailSenderService;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
    private final UserRepository userRepository;

    public ApiResponse<Void> generateOTP(UserEntity user, String title) {
        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < OTP_LENGTH; i++) {
            int index = secureRandom.nextInt(CHARACTERS.length());
            otp.append(CHARACTERS.charAt(index));
        }
        Map<String, Object> otpMap = new HashMap<>();
        otpMap.put("otp", otp.toString());

        OTPEntity otpEntity = new OTPEntity();
        otpEntity.setCode(otp.toString());
        otpEntity.setUser(user);
        otpRepository.save(otpEntity);

        emailSenderService.sendEmail(user.getEmail(), title, "mail-template", otpMap);

        return BuildResponse.buildApiResponse(200, "Vui lòng kiểm tra email của bạn!", null, null);
    }

    public ApiResponse<Void> checkOTP(String code) {
        OTPEntity otpEntity = otpRepository.findByCode(code)
                .orElseThrow(() -> new NotFoundException("Mã OTP không tồn tại!"));
        if (otpEntity.getExpiredAt().isBefore(Instant.now())) {
            throw new NotFoundException("Mã OTP đã hết hạn!");
        }
        return BuildResponse.buildApiResponse(200, "Mã OTP hợp lệ!", null, null);
    }

    public ApiResponse<Void> sendChangePasswordRequest(String email) {
        UserEntity user = userRepository.findByEmailAndAccountType(email, AccountTypeEnum.CREDENTIALS)
                .orElseThrow(() -> new NotFoundException("Email này chưa được đăng kí!"));
        return this.generateOTP(user, "Yêu cầu đổi mật khẩu!");
    }

    @Transactional
    public ApiResponse<Void> changePassword(ChangePasswordRequest otpRequest) {
        OTPEntity otp = otpRepository.findByCode(otpRequest.getCode())
                .orElseThrow(() -> new NotFoundException("Mã OTP sai!"));

        Instant now = Instant.now();
        if (now.isAfter(otp.getExpiredAt())) {
            throw new NotFoundException("Mã OTP của bạn đã hết hạn!");
        }

        if (!otpRequest.getPassword().equals(otpRequest.getRePassword())) {
            throw new UserException("Mật khẩu không khớp!");
        }

        UserEntity user = otp.getUser();
        user.setPassword(passwordEncoder.encode(otpRequest.getPassword()));
        user.setOtp(null);
        userRepository.save(user);

        otp.setUser(null);
        otpRepository.delete(otp);

        return BuildResponse.buildApiResponse(200, "Đổi mật khẩu thành công!", null, null);
    }

    @Transactional
    public ApiResponse<Void> activeAccount(String code) {
        OTPEntity otp = otpRepository.findByCode(code)
                .orElseThrow(() -> new NotFoundException("Mã OTP sai!"));

        Instant now = Instant.now();
        if (now.isAfter(otp.getExpiredAt())) {
            throw new NotFoundException("Mã OTP của bạn đã hết hạn!");
        }

        UserEntity user = otp.getUser();
        user.setActive(true);
        user.setOtp(null);
        userRepository.save(user);

        otp.setUser(null);
        otpRepository.delete(otp);
        return BuildResponse.buildApiResponse(200, "Kích hoạt tài khoản thành công!", null, null);
    }
}