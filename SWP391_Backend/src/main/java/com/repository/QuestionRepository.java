package com.repository;

import com.entity.QuestionEntity;
import com.repository.custom.JpaSpecificationRepository;

public interface QuestionRepository extends JpaSpecificationRepository<QuestionEntity, Long> {
    boolean existsByTitle(String title);
    QuestionEntity findByTitle(String title);
}
