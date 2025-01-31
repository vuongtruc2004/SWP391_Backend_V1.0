package com.repository;

import com.entity.CourseEntity;
import com.repository.custom.JpaSpecificationRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface CourseRepository extends JpaSpecificationRepository<CourseEntity, Long> {
    boolean existsBy();

    public List<CourseEntity> findByCourseNameContaining(String name);
}
