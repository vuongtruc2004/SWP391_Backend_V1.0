package com.repository;

import com.entity.UserProgressEntity;
import com.repository.custom.JpaSpecificationRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserProgressRepository extends JpaSpecificationRepository<UserProgressEntity, Long> {

    @Query("select count(up.progressId) " +
            "from UserProgressEntity up " +
            "where up.userId = :userId and up.courseId =:courseId")
    long countNumberOfCompletedVideosAndDocuments(@Param("userId") Long userId, @Param("courseId") Long courseId);

    List<UserProgressEntity> findAllByCourseIdAndUserId(Long courseId, Long userId);

    boolean existsByUserIdAndCourseIdAndLessonIdAndVideoId(Long userId, Long courseId, Long lessonId, Long videoId);

    boolean existsByUserIdAndCourseIdAndLessonIdAndDocumentId(Long userId, Long courseId, Long lessonId, Long documentId);

    boolean existsByUserIdAndCourseIdAndLessonIdAndDocumentIdAndVideoId(Long userId, Long courseId, Long lessonId, Long documentId, Long videoId);
}
