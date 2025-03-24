package com.repository;

import com.entity.UserEntity;
import com.entity.UserNotificationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserNotificationRepository extends JpaRepository<UserNotificationEntity, Long> {

    Page<UserNotificationEntity> findAllByIsReadFalseAndUser_UserId(Pageable pageable, Long userId);

    Page<UserNotificationEntity> findAllByUser_UserId(Pageable pageable, Long userId);

    List<UserNotificationEntity> findAllByIsReadFalseAndUser_UserId(Long userId);
    Optional<UserNotificationEntity> findByNotification_NotificationIdAndUser_UserId(Long notificationId, Long userId);

    @Modifying
    @Query(value = "insert into user_notifications (user_id, notification_id, is_read) " +
            "select user_id, :notificationId, false from users", nativeQuery = true)
    void insertUserNotification(@Param("notificationId") Long notificationId);

    @Modifying
    @Query(value = "insert into user_notifications (user_id, notification_id, is_read) " +
            "select user_id, :notificationId, false from users where user_id in :userIds", nativeQuery = true)
    void insertUserSpecificationNotifications(@Param("notificationId") Long notificationId, @Param("emails") List<Long> userIds);

    Page<UserNotificationEntity> findByNotification_NotificationId(Long notificationId, Pageable pageable);
    void deleteAllByNotification_NotificationId(Long notificationId);

    void deleteUserNotificationEntityByUserNotificationId(Long userNotificationId);
}
