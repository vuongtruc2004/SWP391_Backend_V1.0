package com.entity;

import com.util.enums.NotificationStatusEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "notifications")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NotificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    Long notificationId;

    String title;

    @Column(columnDefinition = "LONGTEXT")
    String content;

    @Enumerated(EnumType.STRING)
    NotificationStatusEnum status;

    @Column(name = "set_date")
    Instant setDate;

    @Column(name = "created_at")
    Instant createdAt;

    @Column(name = "udpated_at")
    Instant updatedAt;

    Boolean global;

    @OneToMany(mappedBy = "notification")
    Set<UserNotificationEntity> userNotifications;

    @PrePersist
    public void handlePrePersist() {
        this.createdAt = Instant.now();
    }

    @PreUpdate
    public void handlePreUpdate() {
        this.updatedAt = Instant.now();
    }
}
