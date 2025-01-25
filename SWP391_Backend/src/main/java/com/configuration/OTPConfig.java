package com.configuration;

import com.entity.OTPEntity;
import com.entity.UserEntity;
import com.repository.OTPRepository;
import com.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
@RequiredArgsConstructor
public class OTPConfig {
    private final OTPRepository otpRepository;
    private final UserRepository userRepository;

    @Scheduled(cron = "0 * * * * *")
    @Transactional
    public void removeOTP(){
        List<OTPEntity> list = otpRepository.findAllByActive(false);
        for(OTPEntity otp : list){
            if(otp.getExpiresAt().truncatedTo(ChronoUnit.MINUTES).toString().equals(LocalTime.now().truncatedTo(ChronoUnit.MINUTES).toString())){
                UserEntity user = otp.getUser();
                if(user != null && user.getActive() == true){
                    user.setOtp(null);
                    otp.setUser(null);
                }
                otpRepository.delete(otp);
            }
        }
    }

}