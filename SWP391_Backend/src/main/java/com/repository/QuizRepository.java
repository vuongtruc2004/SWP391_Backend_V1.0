package com.repository;

import com.entity.QuizEntity;
import com.repository.custom.JpaSpecificationRepository;

import java.util.Optional;

public interface QuizRepository extends JpaSpecificationRepository<QuizEntity, Long> {
    boolean existsByTitle(String title);
    Optional<QuizEntity> findByTitle(String title);
}
