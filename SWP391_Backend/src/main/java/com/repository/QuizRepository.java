package com.repository;

import com.entity.QuizEntity;
import com.repository.custom.JpaSpecificationRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface QuizRepository extends JpaSpecificationRepository<QuizEntity, Long> {
    boolean existsByTitle(String title);
    Optional<QuizEntity> findByTitle(String title);
    @Query("select count(q) from QuizEntity q where q.chapter.course.courseId = :courseId")
    Long countByCourseId(@Param("courseId") Long courseId);
}
