package com.service;

import com.dto.request.OTPRequest;
import com.entity.OTPEntity;
import com.entity.UserEntity;
import com.exception.custom.NotFoundException;
import com.repository.OTPRepository;
import com.repository.UserRepository;
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

    public String generateOTP(UserEntity user, String title) {
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
        return "Vui lòng kiểm tra email của bạn!";
    }

    @Transactional
    public String changePassword(OTPRequest otpRequest) {
        OTPEntity otp = otpRepository.findByCode(otpRequest.getCode())
                .orElseThrow(() -> new NotFoundException("OTP code not found!"));

        Instant now = Instant.now();
        if (now.isAfter(otp.getExpiredAt())) {
            throw new NotFoundException("OTP code expired!");
        }

        UserEntity user = otp.getUser();
        user.setPassword(passwordEncoder.encode(otpRequest.getNewPassword()));
        user.setOtp(null);
        userRepository.save(user);

        otp.setUser(null);
        otpRepository.delete(otp);
        return "Đổi mật khẩu thành công!";
    }

    @Transactional
    public String activeAccount(String code) {
        OTPEntity otp = otpRepository.findByCode(code)
                .orElseThrow(() -> new NotFoundException("OTP code not found!"));

        Instant now = Instant.now();
        if (now.isAfter(otp.getExpiredAt())) {
            throw new NotFoundException("OTP code expired!");
        }
        
        UserEntity user = otp.getUser();
        user.setActive(true);
        user.setOtp(null);
        userRepository.save(user);

        otp.setUser(null);
        otpRepository.delete(otp);
        return "Bạn đã kích hoạt tài khoản thành công!";
    }
}