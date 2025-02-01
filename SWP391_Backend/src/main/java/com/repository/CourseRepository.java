package com.repository;

import com.entity.CourseEntity;
import com.repository.custom.JpaSpecificationRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaSpecificationRepository<CourseEntity, Long> {
}
