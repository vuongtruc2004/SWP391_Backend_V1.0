package com.repository;

import com.entity.RateEntity;
import com.repository.custom.JpaSpecificationRepository;

public interface RateRepository extends JpaSpecificationRepository<RateEntity, Long> {

    int countRateEntitiesByCourse_CourseIdAndStars(Long courseCourseId, Integer stars);
}
