package com.repository;

import com.entity.NotificationEntity;
import com.util.enums.NotificationStatusEnum;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Set;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {
    @Query("select n from NotificationEntity n " +
            "left join UserNotificationEntity u on n.notificationId = u.notification.notificationId " +
            "where (n.global = true OR u.user.userId = :userId) and n.notificationId not in (:readIds) "+
            "order by n.createdAt desc ")
    List<NotificationEntity> getNotificationEntitiesByGlobalOrUserId(@Param("userId") Long userId, Set<Long> readIds);

    @Query("select n from NotificationEntity n " +
            "left join UserNotificationEntity u on n.notificationId = u.notification.notificationId " +
            "where (n.global = true OR u.user.userId = :userId) "+
            "order by n.createdAt desc ")
    List<NotificationEntity> getNotificationEntitiesByGlobalOrUserId(@Param("userId") Long userId);
    Page<NotificationEntity> findAll(Specification<NotificationEntity> specification, Pageable pageable);
    void deleteNotificationEntityByNotificationId(Long notificationId);

    Set<NotificationEntity> getNotificationEntitiesByStatusAndAndCreatedAt(NotificationStatusEnum status, Instant createdAt);

    @Modifying
    @Query("select n.notificationId from NotificationEntity n where n.status = :status and n.setDate = :setDate")
    Set<Long> findAllNotificationId(@Param("status") NotificationStatusEnum status, @Param("setDate") Instant setDate);

    @Modifying
    @Transactional
    @Query("update NotificationEntity n set n.status = :newStatus where n.notificationId in (:ids)")
    int updateStatus(@Param("newStatus") NotificationStatusEnum newStatus, @Param("ids") Set<Long> ids);
}
