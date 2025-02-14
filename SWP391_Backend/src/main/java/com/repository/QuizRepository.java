package com.repository;

import com.entity.QuestionEntity;
import com.entity.QuizEntity;
import com.repository.custom.JpaSpecificationRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository extends JpaSpecificationRepository<QuizEntity, Long> {
    List<QuizEntity> findByTitle(String title);
    List<QuizEntity> findByQuestions(QuestionEntity question);
}
