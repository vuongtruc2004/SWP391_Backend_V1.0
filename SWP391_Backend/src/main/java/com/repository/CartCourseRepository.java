package com.repository;

import com.entity.CartCourseEntity;
import com.entity.CartEntity;
import com.entity.CourseEntity;
import com.repository.custom.JpaSpecificationRepository;

import java.util.Optional;

public interface CartCourseRepository extends JpaSpecificationRepository<CartCourseEntity, Long> {
    Optional<CartCourseEntity> findByCartAndCourse(CartEntity cart, CourseEntity course);
}
