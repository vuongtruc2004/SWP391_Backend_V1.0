package com.service;

import com.dto.response.ApiResponse;
import com.dto.response.NotificationResponse;
import com.entity.UserEntity;
import com.helper.UserServiceHelper;
import com.repository.NotificationRepository;
import com.util.BuildResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final SimpMessagingTemplate messagingTemplate;
    private final NotificationRepository notificationRepository;
    private final UserServiceHelper userServiceHelper;
    private final ModelMapper modelMapper;

    public void purchaseSuccessNotification(String fullname) {
        String payload = "Cảm ơn bạn " + fullname + " đã tin tưởng LearnGo!";
        messagingTemplate.convertAndSend("/topic/purchased", payload);
    }

    public ApiResponse<List<NotificationResponse>> getAllNotifications() {
        UserEntity user = userServiceHelper.extractUserFromToken();
        List<NotificationResponse> notifications = notificationRepository.getNotificationEntitiesByGlobalOrUserId(user.getUserId())
                .stream()
                .map(notificationEntity -> {
                    NotificationResponse notificationResponse = modelMapper.map(notificationEntity, NotificationResponse.class);
                    return notificationResponse;
                }).toList();
        return BuildResponse.buildApiResponse(
                HttpStatus.OK.value(),
                "Thành công!",
                "Lấy danh sách thông báo thành công!",
                notifications
        );
    }
}
