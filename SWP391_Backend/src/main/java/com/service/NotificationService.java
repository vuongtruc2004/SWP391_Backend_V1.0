package com.service;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {
    
    private final SimpMessagingTemplate messagingTemplate;

    public void purchaseSuccessNotification(String fullname) {
        System.out.println("Purchase Success Notification");
        messagingTemplate.convertAndSend("/topic/purchased", fullname + " đã mua khóa học thành công!");
    }
}
