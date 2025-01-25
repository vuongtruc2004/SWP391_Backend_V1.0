package com.service;

import com.dto.request.ActiveOTPRequest;
import com.dto.request.OTPRequest;
import com.entity.OTPEntity;
import com.entity.UserEntity;
import com.exception.AppException;
import com.repository.OTPRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
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
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

    public String generateOTP(UserEntity user, String title) {
        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < OTP_LENGTH; i++) {
            int index = secureRandom.nextInt(CHARACTERS.length());
            otp.append(CHARACTERS.charAt(index));
        }
        Map<String, Object> otpMap = new HashMap<>();
        otpMap.put("otp", otp.toString());
        OTPEntity otpEntity = new OTPEntity();
        otpEntity.setToken(otp.toString());
        otpEntity.setUser(user);
        otpRepository.save(otpEntity);
        emailSenderService.sendEmail(user.getEmail(), title, "mail-template", otpMap);
        return "Vui lòng kiểm tra email của bạn.";
    }

    @Transactional
    public String changePassword(OTPRequest otpRequest) {
        OTPEntity otp = otpRepository.findByToken(otpRequest.getOtp());
        if (otp == null) {
            throw new AppException("Mã OTP không hợp lệ.");
        }
        UserEntity user = otp.getUser();
        user.setPassword(passwordEncoder.encode(otpRequest.getNewPassword()));
        user.setOtp(null);
        otp.setUser(null);
        otpRepository.delete(otp);
        return "Đổi mật khẩu thành công.";
    }

    @Transactional
    public String checkToken(ActiveOTPRequest request) {
        OTPEntity otp = otpRepository.findByToken(request.getToken());
        if (otp == null) {
            throw new AppException("Mã OTP không hợp lệ.");
        }
        UserEntity user = otp.getUser();
        user.setActive(true);
        user.setOtp(null);
        otp.setUser(null);
        otpRepository.delete(otp);
        return "Bạn đã kích hoạt tài khoản thành công.";
    }
}