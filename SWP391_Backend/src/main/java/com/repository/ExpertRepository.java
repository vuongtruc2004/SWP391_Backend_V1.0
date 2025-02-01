package com.repository;

import com.entity.ExpertEntity;
import com.repository.custom.JpaSpecificationRepository;

import java.util.Optional;

public interface ExpertRepository extends JpaSpecificationRepository<ExpertEntity, Long> {
    Optional<ExpertEntity> findByUser_Username(String username);
}
