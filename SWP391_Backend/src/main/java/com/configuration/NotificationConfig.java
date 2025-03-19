package com.configuration;

import com.service.NotificationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationConfig {
    private final NotificationService notificationService;

    @Scheduled(cron = "0 * * * * ?")
    @Transactional
    public void sendNotificationByTime(){
        notificationService.updateStatusOfNotification();


    }
}
