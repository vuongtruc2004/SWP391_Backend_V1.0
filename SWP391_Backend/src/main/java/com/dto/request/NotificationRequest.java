package com.dto.request;

import com.util.enums.NotificationStatusEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NotificationRequest {
    String title;

    String content;

    @Enumerated(EnumType.STRING)
    NotificationStatusEnum status;

    Boolean global;

    List<Long> userIds;

    Instant setDate;
}
