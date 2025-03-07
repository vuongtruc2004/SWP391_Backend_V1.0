package com.repository;

import com.entity.ChatEntity;
import com.entity.UserEntity;
import com.repository.custom.JpaSpecificationRepository;

import java.util.Optional;

public interface ChatRepository extends JpaSpecificationRepository<ChatEntity, Long> {
    Optional<ChatEntity> findTopByUser_UserIdOrderByCreatedAtDesc(Long userId);

    Long user(UserEntity user);
}
