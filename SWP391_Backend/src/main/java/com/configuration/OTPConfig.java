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

    @Scheduled(fixedRate = 60000) // Chạy mỗi 60 giây
    @Transactional
    public void removeAllExpiredOTP() {
        Set<OTPEntity> expiredOTP = otpRepository.findAllExpiredOTP(Instant.now());
        if (expiredOTP.isEmpty()) {
            System.out.println("Không có OTP hết hạn!");
            return;
        }

        Set<UserEntity> remainUsers = new HashSet<>();
        for (OTPEntity otpEntity : expiredOTP) {
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
        otpRepository.deleteAll(expiredOTP);

        System.out.println("Deleted expired OTP successfully! (" + expiredOTP.size() + ")");
    }
}
