package com.configuration;

import com.entity.OTPEntity;
import com.repository.OTPRepository;
import com.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class OTPConfig {
    private final OTPRepository otpRepository;
    private final UserRepository userRepository;

    @Scheduled(fixedRate = 60000) // Chạy mỗi 60 giây
    @Transactional
    public void removeAllExpiredOTP() {
        Set<OTPEntity> expiredOTP = otpRepository.findAllExpiredOTP(Instant.now());
        if (expiredOTP.isEmpty()) return;

        for (OTPEntity otpEntity : expiredOTP) {
            if (otpEntity.getUser() != null) {
                otpEntity.getUser().setOtp(null);
            }
        }
        userRepository.saveAll(expiredOTP.stream().map(OTPEntity::getUser).toList());
        otpRepository.deleteAll(expiredOTP);

        System.out.println("Deleted expired OTP successfully! (" + expiredOTP.size() + ")");
    }
}
