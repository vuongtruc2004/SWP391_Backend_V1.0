package com.repository;

import com.entity.QuizEntity;
import com.repository.custom.JpaSpecificationRepository;

public interface QuizRepository extends JpaSpecificationRepository<QuizEntity, Long> {
}
