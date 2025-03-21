package com.repository;

import com.entity.QuizAttemptEntity;
import com.entity.UserAnswerEntity;
import com.repository.custom.JpaSpecificationRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface UserAnswerRepository extends JpaSpecificationRepository<UserAnswerEntity, Long> {

    @Modifying
    @Query("DELETE FROM UserAnswerEntity ua WHERE ua.quizAttempt = :quizAttempt")
    void deleteAllByQuizAttempt(@Param("quizAttempt") QuizAttemptEntity quizAttempt);

    Set<UserAnswerEntity> findAllByQuizAttempt_QuizAttemptId(Long quizAttemptQuizAttemptId);
}
