package com.repository;

import com.entity.RateEntity;
import com.repository.custom.JpaSpecificationRepository;

import java.util.Optional;

public interface RateRepository extends JpaSpecificationRepository<RateEntity, Long> {

    int countRateEntitiesByCourse_CourseIdAndStars(Long courseCourseId, Integer stars);

    Optional<RateEntity> findByCourse_CourseIdAndUser_UserId(Long courseId, Long userId);

}
