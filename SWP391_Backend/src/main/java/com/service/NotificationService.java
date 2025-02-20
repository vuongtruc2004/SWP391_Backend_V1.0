package com.service;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final SimpMessagingTemplate messagingTemplate;

    public void purchaseSuccessNotification(String fullname) {
        String payload = "Cảm ơn bạn " + fullname + " đã tin tưởng LearnGo!";
        messagingTemplate.convertAndSend("/topic/purchased", payload);
    }
}
