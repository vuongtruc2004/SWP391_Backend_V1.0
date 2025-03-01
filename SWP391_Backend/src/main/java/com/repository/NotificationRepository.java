package com.repository;

import com.entity.NotificationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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

    @Query(value = """
    SELECT * FROM notifications
    ORDER BY 
        CASE 
            WHEN udpated_at IS NOT NULL THEN udpated_at
            ELSE created_at
        END DESC,
        created_at DESC
    """,
            countQuery = "SELECT COUNT(*) FROM notifications",
            nativeQuery = true)
    Page<NotificationEntity> findAllNotificationSorted(Specification<NotificationEntity> specification, Pageable pageable);
}
