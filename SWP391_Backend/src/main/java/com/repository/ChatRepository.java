package com.repository;

import com.entity.ChatEntity;
import com.repository.custom.JpaSpecificationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.Optional;

public interface ChatRepository extends JpaSpecificationRepository<ChatEntity, Long> {
    Page<ChatEntity> findAllByUser_UserIdAndCreatedAtIsGreaterThanEqualOrderByCreatedAtDesc(Long userId, Instant createdAt, Pageable pageable);

    Optional<ChatEntity> findByUser_UserIdAndChatId(Long userUserId, Long chatId);

}
