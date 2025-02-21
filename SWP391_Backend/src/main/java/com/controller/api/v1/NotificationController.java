package com.controller.api.v1;

import com.dto.response.ApiResponse;
import com.dto.response.NotificationResponse;
import com.dto.response.PageDetailsResponse;
import com.dto.response.UserNotificationResponse;
import com.service.NotificationService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notifications")
public class NotificationController {
    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping
    public ResponseEntity<PageDetailsResponse<List<UserNotificationResponse>>> getNotifications(@RequestParam(name = "status", required = false) String status, Pageable pageable) {
        return ResponseEntity.ok(notificationService.getAllNotifications(status, pageable));
    }

    @PostMapping("/{notificationId}")
    public ResponseEntity<ApiResponse<String>> readANotification(@PathVariable("notificationId") Long notificationId) {
        return ResponseEntity.ok(notificationService.readANotification(notificationId));
    }

    @PostMapping("/all")
    public ResponseEntity<ApiResponse<String>> readAllNotifications() {
        return ResponseEntity.ok(notificationService.readAllNotifications());
    }

    @DeleteMapping("/{notificationId}")
    public ResponseEntity<ApiResponse<String>> deleteANotification(@PathVariable("notificationId") Long notificationId) {
        return ResponseEntity.ok(notificationService.deleteNotification(notificationId));
    }
}
