package com.repository;

import com.entity.ExpertEntity;
import com.entity.QuizEntity;
import com.repository.custom.JpaSpecificationRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface QuizRepository extends JpaSpecificationRepository<QuizEntity, Long> {
    Optional<QuizEntity> findByQuizIdAndPublishedTrue(Long quizId);

    boolean existsByTitle(String title);

    @Query("select count(q) from QuizEntity q where q.chapter.course.courseId = :courseId")
    Long countByCourseId(@Param("courseId") Long courseId);
    
    Optional<QuizEntity> findByQuizIdAndExpert(Long quizId, ExpertEntity expert);
}