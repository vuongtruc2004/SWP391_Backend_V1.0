package com.service;

import com.dto.response.ApiResponse;
import com.dto.response.NotificationResponse;
import com.dto.response.PageDetailsResponse;
import com.dto.response.UserNotificationResponse;
import com.entity.NotificationEntity;
import com.entity.UserEntity;
import com.entity.UserNotificationEntity;
import com.exception.custom.NotFoundException;
import com.helper.NotificationServiceHelper;
import com.helper.UserServiceHelper;
import com.repository.NotificationRepository;
import com.repository.UserNotificationRepository;
import com.util.BuildResponse;
import com.util.enums.MessageStatusNotificationEnum;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final SimpMessagingTemplate messagingTemplate;
    private final NotificationRepository notificationRepository;
    private final UserServiceHelper userServiceHelper;
    private final ModelMapper modelMapper;
    private final UserNotificationRepository userNotificationRepository;
    private final NotificationServiceHelper notificationServiceHelper;

    public void purchaseSuccessNotification(String fullname) {
        String payload = "Cảm ơn bạn " + fullname + " đã tin tưởng LearnGo!";
        messagingTemplate.convertAndSend("/topic/purchased", payload);
    }

    public void readSuccessNotification() {
        messagingTemplate.convertAndSend("/topic/purchased", MessageStatusNotificationEnum.READ.toString());
    }

    public PageDetailsResponse<List<UserNotificationResponse>> getAllNotifications(String status, Pageable pageable) {
        UserEntity user = userServiceHelper.extractUserFromToken();

        Page<UserNotificationEntity> page;
        if(status != null && status.equalsIgnoreCase("unread")){
            page = userNotificationRepository.findAllByIsReadFalseAndUser_UserId(pageable, user.getUserId());
        } else {
            page = userNotificationRepository.findAllByUser_UserId(pageable, user.getUserId());
        }
        return notificationServiceHelper.convertToPageDetailsResponse(page);

    }

    public ApiResponse<String> readANotification(Long notificationId) {
        UserEntity user = userServiceHelper.extractUserFromToken();
        UserNotificationEntity userNotificationEntity = userNotificationRepository.findByNotification_NotificationIdAndUser_UserId(notificationId, user.getUserId())
                        .orElseThrow(()-> new NotFoundException("Không tìm thấy thông báo!"));
        userNotificationEntity.setIsRead(true);
        userNotificationRepository.save(userNotificationEntity);
        readSuccessNotification();
        return BuildResponse.buildApiResponse(
                HttpStatus.OK.value(),
                "Thành công!",
                null,
                "Đã đọc!"
        );
    }

    public ApiResponse<String> readAllNotifications() {
        UserEntity user = userServiceHelper.extractUserFromToken();
        List<UserNotificationEntity> userNotificationEntities = userNotificationRepository.findAllByIsReadFalseAndUser_UserId(user.getUserId());
        for(UserNotificationEntity userNotificationEntity : userNotificationEntities){
            userNotificationEntity.setIsRead(true);
            userNotificationRepository.save(userNotificationEntity);
        }
        readSuccessNotification();
        return BuildResponse.buildApiResponse(
                HttpStatus.OK.value(),
                "Thành công!",
                null,
                "Đã đọc!"
        );
    }

    public ApiResponse<String> deleteNotification(Long notificationId) {
        UserEntity user = userServiceHelper.extractUserFromToken();
        UserNotificationEntity userNotificationEntity = userNotificationRepository.findByNotification_NotificationIdAndUser_UserId(notificationId, user.getUserId())
                .orElseThrow(()-> new NotFoundException("Không tìm thấy thông báo!"));
        userNotificationRepository.delete(userNotificationEntity);
        readSuccessNotification();
        return BuildResponse.buildApiResponse(
                HttpStatus.OK.value(),
                "Thành công!",
                null,
                "Đã xóa!"
        );
    }
}
