package com.repository;

import com.entity.ChapterEntity;
import com.entity.QuizAttemptEntity;
import com.entity.UserAnswerEntity;
import com.repository.custom.JpaSpecificationRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserAnswerRepository extends JpaSpecificationRepository<UserAnswerEntity, Long> {
    void deleteByQuizAttempt(QuizAttemptEntity quizAttempt);
    List<UserAnswerEntity> findByQuizAttempt(QuizAttemptEntity quizAttempt);
}
