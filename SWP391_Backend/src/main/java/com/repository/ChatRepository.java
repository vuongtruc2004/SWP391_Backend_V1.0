package com.repository;

import com.entity.ChatEntity;
import com.entity.UserEntity;
import com.repository.custom.JpaSpecificationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.Optional;

public interface ChatRepository extends JpaSpecificationRepository<ChatEntity, Long> {
    Optional<ChatEntity> findTopByUser_UserIdOrderByCreatedAtDesc(Long userId);

    Long user(UserEntity user);

    Page<ChatEntity> findAllByUser_UserIdAndCreatedAtIsGreaterThanEqualOrderByCreatedAtDesc(Long userId, Instant createdAt, Pageable pageable);
}
