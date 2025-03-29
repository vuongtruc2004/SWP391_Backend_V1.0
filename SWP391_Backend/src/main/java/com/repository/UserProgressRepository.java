package com.repository;

import com.entity.UserEntity;
import com.entity.UserProgressEntity;
import com.repository.custom.JpaSpecificationRepository;

import java.util.List;

public interface UserProgressRepository extends JpaSpecificationRepository<UserProgressEntity, Long> {
    boolean existsByUser_UserIdAndCourseIdAndChapterIdAndLessonId(Long userId, Long courseId, Long chapterId, Long lessonId);

    boolean existsByUser_UserIdAndCourseIdAndChapterIdAndQuizId(Long userId, Long courseId, Long chapterId, Long quizId);

    Long countByCourseIdAndUser(Long courseId, UserEntity user);

    List<UserProgressEntity> findAllByChapterId(Long chapterId);

    List<UserProgressEntity> findAllByLessonId(Long lessonId);
}
