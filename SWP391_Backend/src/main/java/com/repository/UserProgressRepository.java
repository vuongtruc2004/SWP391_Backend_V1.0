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
}
