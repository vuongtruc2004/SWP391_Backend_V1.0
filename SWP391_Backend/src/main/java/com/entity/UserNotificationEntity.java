package com.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonManagedReference
    UserEntity user;

    @ManyToOne
    @JoinColumn(name = "notification_id")
    @JsonManagedReference
    NotificationEntity notification;

    Boolean isRead;
}
