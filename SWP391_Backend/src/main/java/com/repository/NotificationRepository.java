package com.repository;

import com.entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {
    @Query("select n from NotificationEntity n " +
            "left join UserNotificationEntity u on n.notificationId = u.notification.notificationId " +
            "where n.global = true OR u.user.userId = :userId " +
            "order by n.createdAt desc ")
    List<NotificationEntity> getNotificationEntitiesByGlobalOrUserId(@Param("userId") Long userId);
}
