package com.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserNotificationResponse {
    Long userNotificationId;

    UserResponse user;
    @JsonIgnoreProperties("userNotifications")
    NotificationResponse notification;
    Boolean isRead;
}
