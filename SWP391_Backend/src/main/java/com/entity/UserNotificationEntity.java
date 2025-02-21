package com.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_notifications")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserNotificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_notification_id")
    Long userNotificationId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    UserEntity user;

    @ManyToOne
    @JoinColumn(name = "notification_id")
    NotificationEntity notification;

    Boolean isRead;

    @PrePersist
    public void handlePrePersist(){
        isRead = false;
    }
}
