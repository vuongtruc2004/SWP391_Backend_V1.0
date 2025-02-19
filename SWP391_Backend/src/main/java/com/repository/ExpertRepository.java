package com.repository;

import com.entity.CourseEntity;
import com.entity.ExpertEntity;
import com.repository.custom.JpaSpecificationRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ExpertRepository extends JpaSpecificationRepository<ExpertEntity, Long> {
    ExpertEntity findByCourses(CourseEntity course);

    @Query("select count(distinct u.userId) from ExpertEntity e " +
            "join e.courses c " +
            "join c.users u " +
            "where e.expertId = :expertId")
    Integer countTotalStudents(@Param("expertId") Long expertId);
}
