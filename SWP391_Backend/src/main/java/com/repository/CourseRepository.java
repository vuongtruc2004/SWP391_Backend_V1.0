package com.repository;

import com.entity.CourseEntity;
import com.entity.UserEntity;
import com.repository.custom.JpaSpecificationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaSpecificationRepository<CourseEntity, Long> {
    Page<CourseEntity> findAllByUsers(UserEntity user, Pageable pageable);

    @Query("SELECT c FROM CourseEntity c ORDER BY size(c.users) desc ")
    Page<CourseEntity> findCoursesAndOrderByPurchasersDesc(Pageable pageable);

    @Query("SELECT MIN(c.originalPrice) FROM CourseEntity c")
    Double findMinPrice(); // Giá nhỏ nhất

    @Query("SELECT MAX(c.originalPrice) FROM CourseEntity c")
    Double findMaxPrice(); // Giá lớn nhất
}
