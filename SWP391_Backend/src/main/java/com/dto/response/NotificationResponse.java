package com.dto.response;

import com.util.enums.NotificationStatusEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NotificationResponse {
    Long notificationId;
    String title;
    String content;
    @Enumerated(EnumType.STRING)
    NotificationStatusEnum status;
    Instant setDate;
    Instant createdAt;
    Boolean global;
    Set<UserNotificationResponse> userNotifications;
}
