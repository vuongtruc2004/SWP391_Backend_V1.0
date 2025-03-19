package com.repository;

import com.entity.LessonEntity;
import com.repository.custom.JpaSpecificationRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LessonRepository extends JpaSpecificationRepository<LessonEntity, Long> {
    @Query("SELECT COUNT(l) FROM LessonEntity l WHERE l.chapter.course.courseId = :courseId")
    Long countLessonsByCourse(@Param("courseId") Long courseId);
}
