package com.repository;

import com.entity.UserProgressEntity;
import com.repository.custom.JpaSpecificationRepository;

import java.util.Set;

public interface UserProgressRepository extends JpaSpecificationRepository<UserProgressEntity, Long> {
    long countAllByUser_UserIdAndCourseId(long userId, long courseId);

    Set<UserProgressEntity> findAllByUser_UserIdAndCourseId(Long userId, Long courseId);

    boolean existsByUser_UserIdAndChapterIdAndLessonId(Long userUserId, Long chapterId, Long lessonId);
}
