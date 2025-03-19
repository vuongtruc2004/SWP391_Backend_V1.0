package com.repository;

import com.entity.QuizAttemptEntity;
import com.entity.QuizEntity;
import com.entity.UserEntity;
import com.repository.custom.JpaSpecificationRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface QuizAttemptRepository extends JpaSpecificationRepository<QuizAttemptEntity, Long> {
    @Query("SELECT q FROM QuizAttemptEntity q WHERE q.user = :user AND q.quiz = :quiz ORDER BY q.startTime DESC")
    List<QuizAttemptEntity> findByUserAndQuiz(@Param("user") UserEntity user, @Param("quiz") QuizEntity quiz);


    Integer countByUserAndQuiz(UserEntity user, QuizEntity quiz);
}
