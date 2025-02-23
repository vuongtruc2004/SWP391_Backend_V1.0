package com.service;

import com.dto.request.NotificationRequest;
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
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final SimpMessagingTemplate messagingTemplate;
    private final NotificationRepository notificationRepository;
    private final UserServiceHelper userServiceHelper;
    private final ModelMapper modelMapper;
    private final UserNotificationRepository userNotificationRepository;
    private final NotificationServiceHelper notificationServiceHelper;

    public void purchaseSuccessNotification() {
        messagingTemplate.convertAndSend("/topic/purchased", MessageStatusNotificationEnum.PURCHASED.toString());
    }

    public void readSuccessNotification() {
        messagingTemplate.convertAndSend("/topic/purchased", MessageStatusNotificationEnum.READ.toString());
    }

    public PageDetailsResponse<List<UserNotificationResponse>> getAllNotifications(String status, Pageable pageable) {
        UserEntity user = userServiceHelper.extractUserFromToken();

        Page<UserNotificationEntity> page;
        if (status != null && status.equalsIgnoreCase("unread")) {
            page = userNotificationRepository.findAllByIsReadFalseAndUser_UserId(pageable, user.getUserId());
        } else {
            page = userNotificationRepository.findAllByUser_UserId(pageable, user.getUserId());
        }
        return notificationServiceHelper.convertToPageDetailsResponse(page);

    }

    public ApiResponse<String> readANotification(Long notificationId) {
        UserEntity user = userServiceHelper.extractUserFromToken();
        UserNotificationEntity userNotificationEntity = userNotificationRepository.findByNotification_NotificationIdAndUser_UserId(notificationId, user.getUserId())
                .orElseThrow(() -> new NotFoundException("Không tìm thấy thông báo!"));
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
        for (UserNotificationEntity userNotificationEntity : userNotificationEntities) {
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
    @Transactional
    public ApiResponse<String> deleteNotification(Long notificationId) {
        UserEntity user = userServiceHelper.extractUserFromToken();
        UserNotificationEntity userNotificationEntity = userNotificationRepository.findByNotification_NotificationIdAndUser_UserId(notificationId, user.getUserId())
                .orElseThrow(() -> new NotFoundException("Không tìm thấy thông báo!"));
        userNotificationRepository.delete(userNotificationEntity);
        readSuccessNotification();
        return BuildResponse.buildApiResponse(
                HttpStatus.OK.value(),
                "Thành công!",
                null,
                "Đã xóa!"
        );
    }

    @Transactional
    public ApiResponse<String> createNotification(NotificationRequest notificationRequest) {
        NotificationEntity newNotificationEntity = modelMapper.map(notificationRequest, NotificationEntity.class);
        notificationRepository.save(newNotificationEntity);
        List<UserNotificationEntity> userNotificationEntities = new ArrayList<>();
        if(notificationRequest.getGlobal()){
            userNotificationRepository.insertUserNotification(newNotificationEntity.getNotificationId());

        } else {
            userNotificationRepository.insertUserSpecificationNotifications(newNotificationEntity.getNotificationId(), notificationRequest.getFullname());
        }
        readSuccessNotification();
        return BuildResponse.buildApiResponse(
                HttpStatus.CREATED.value(),
                "Thành Công",
                null,
                "Tạo thông báo thành công"
        );
    }

    public PageDetailsResponse<List<NotificationResponse>> getAllNotificationWithFilter(
            Pageable pageable,
            Specification<NotificationEntity> specification
    ) {
        Page<NotificationEntity> page = notificationRepository.findAll(specification, pageable);
        List<NotificationResponse> userNotificationResponses = page.getContent().stream().map(NotificationEntity -> {
            NotificationResponse userNotificationResponse = modelMapper.map(NotificationEntity, NotificationResponse.class);
            return userNotificationResponse;
        }).toList();
        return BuildResponse.buildPageDetailsResponse(
                page.getNumber()+1,
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements(),
                userNotificationResponses
        );
    }

    public PageDetailsResponse<List<UserNotificationResponse>> getUserNotificationBYNotificationId(Long notificationId, Pageable pageable){
        Page<UserNotificationEntity> page = userNotificationRepository.findByNotification_NotificationId(notificationId, pageable);
        List<UserNotificationResponse> userNotificationResponseList = page.getContent().stream()
                .map(userNotificationEntity -> {
                    UserNotificationResponse userNotificationResponse = modelMapper.map(userNotificationEntity, UserNotificationResponse.class);
                    return userNotificationResponse;
                }).toList();
        return BuildResponse.buildPageDetailsResponse(
                page.getNumber()+1,
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements(),
                userNotificationResponseList
        );
    }

    @Transactional
    public ApiResponse<String> deleteNotificationByNotificationId(Long notificationId) {
        userNotificationRepository.deleteAllByNotification_NotificationId(notificationId);
        notificationRepository.deleteNotificationEntityByNotificationId(notificationId);
        readSuccessNotification();
        return BuildResponse.buildApiResponse(
                HttpStatus.OK.value(),
                "Thành Công",
                "Bạn đã xóa thông báo thành công!",
                "Thành công!"
        );
    }

    @Transactional
    public ApiResponse<String> deleteUserNotification(Long userNotificationId) {
        userNotificationRepository.deleteUserNotificationEntityByUserNotificationId(userNotificationId);
        readSuccessNotification();
        return BuildResponse.buildApiResponse(
                HttpStatus.OK.value(),
                "Thành công!",
                "Bạn đã xóa người nhận thông báo thất bại!",
                "Bạn đã xóa người nhận thông báo thành công!"
        );
    }
}
