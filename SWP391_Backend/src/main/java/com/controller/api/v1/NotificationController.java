package com.controller.api.v1;

import com.dto.request.NotificationRequest;
import com.dto.response.ApiResponse;
import com.dto.response.NotificationResponse;
import com.dto.response.PageDetailsResponse;
import com.dto.response.UserNotificationResponse;
import com.entity.NotificationEntity;
import com.service.NotificationService;
import com.turkraft.springfilter.boot.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping
    public ResponseEntity<PageDetailsResponse<List<UserNotificationResponse>>> getNotifications(@RequestParam(name = "status", required = false) String status, Pageable pageable) {
        return ResponseEntity.ok(notificationService.getAllNotifications(status, pageable));
    }

    @PostMapping("/{notificationId}")
    public ResponseEntity<UserNotificationResponse> readANotification(@PathVariable("notificationId") Long notificationId) {
        return ResponseEntity.ok(notificationService.readANotification(notificationId));
    }

    @PostMapping("/all")
    public ResponseEntity<ApiResponse<String>> readAllNotifications() {
        return ResponseEntity.ok(notificationService.readAllNotifications());
    }

    @DeleteMapping("/{notificationId}")
    public ResponseEntity<UserNotificationResponse> deleteANotification(@PathVariable("notificationId") Long notificationId) {
        return ResponseEntity.ok(notificationService.deleteNotification(notificationId));
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<String>> createANotification(@RequestBody NotificationRequest notificationRequest) {
        return ResponseEntity.ok(notificationService.createNotification(notificationRequest));
    }

    @GetMapping("/admin")
    public ResponseEntity<PageDetailsResponse<List<NotificationResponse>>> getAdminNotifications(
            Pageable pageable,
            @Filter Specification<NotificationEntity> specification
    ) {
        return ResponseEntity.ok(notificationService.getAllNotificationWithFilter(pageable, specification));
    }

    @DeleteMapping("/admin/{notificationId}")
    public ResponseEntity<ApiResponse<String>> deleteAdminNotification(@PathVariable("notificationId") Long notificationId) {
        return ResponseEntity.ok(notificationService.deleteNotificationByNotificationId(notificationId));
    }

    @GetMapping("/detail/{notificationId}")
    public ResponseEntity<PageDetailsResponse<List<UserNotificationResponse>>> getUserNotifications(
            @PathVariable("notificationId") Long notificationId,
            Pageable pageable
    ) {
        return ResponseEntity.ok(notificationService.getUserNotificationBYNotificationId(notificationId, pageable));
    }

    @DeleteMapping("/delete-user/{userNotificationId}")
    public ResponseEntity<ApiResponse<UserNotificationResponse>> deleteUserNotification(@PathVariable("userNotificationId") Long userNotificationId) {
        return ResponseEntity.ok(notificationService.deleteUserNotification(userNotificationId));
    }
}
