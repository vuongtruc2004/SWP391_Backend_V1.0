package com.repository;

import com.entity.QuizAttemptEntity;
import com.entity.QuizEntity;
import com.entity.UserEntity;
import com.repository.custom.JpaSpecificationRepository;

import java.util.List;
import java.util.Optional;

public interface QuizAttemptRepository extends JpaSpecificationRepository<QuizAttemptEntity, Long> {
    Optional<QuizAttemptEntity> findTopByUserAndQuizOrderByStartTimeDesc(UserEntity user, QuizEntity quiz);

    Integer countByUserAndQuiz(UserEntity user, QuizEntity quiz);

    List<QuizAttemptEntity> findAllByUserAndQuizOrderByStartTimeDesc(UserEntity user, QuizEntity quiz);
}
