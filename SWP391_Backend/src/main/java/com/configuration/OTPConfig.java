package com.configuration;

import com.entity.OTPEntity;
import com.entity.UserEntity;
import com.repository.OTPRepository;
import com.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class OTPConfig {
    private final OTPRepository otpRepository;
    private final UserRepository userRepository;

    @Scheduled(fixedRate = 90000) // Chạy mỗi 90 giây
    @Transactional
    public void removeAllExpiredOTP() {
        Set<OTPEntity> expiredOTPCode = otpRepository.findAllExpiredOTP(Instant.now());
        if (expiredOTPCode.isEmpty()) {
            System.out.println("Không có OTP hết hạn!");
            return;
        }

        Set<UserEntity> remainUsers = new HashSet<>();
        for (OTPEntity otpEntity : expiredOTPCode) {
            UserEntity user = otpEntity.getUser();
            if (user != null) {
                user.setOtp(null);
                if (Boolean.FALSE.equals(user.getActive())) {
                    userRepository.delete(user);
                } else {
                    remainUsers.add(user);
                }
            }
        }
        userRepository.saveAll(remainUsers);
        otpRepository.deleteAll(expiredOTPCode);

        System.out.println("Deleted expired OTP successfully! (" + expiredOTPCode.size() + ")");
    }
}
