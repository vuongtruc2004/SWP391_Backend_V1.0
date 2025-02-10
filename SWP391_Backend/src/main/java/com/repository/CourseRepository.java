package com.repository;

import com.entity.CourseEntity;
import com.entity.UserEntity;
import com.repository.custom.JpaSpecificationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CourseRepository extends JpaSpecificationRepository<CourseEntity, Long> {
    Page<CourseEntity> findAllByUsers(UserEntity user, Pageable pageable);

    @Query("SELECT c FROM CourseEntity c ORDER BY size(c.users) desc ")
    Page<CourseEntity> findCoursesAndOrderByPurchasersDesc(Pageable pageable);

    @Query("SELECT c.courseId FROM CourseEntity c ORDER BY " +
            "CASE WHEN :asc = true THEN size(c.users) END ASC, " +
            "CASE WHEN :asc = false THEN size(c.users) END DESC")
    Set<Long> findCourseIdsAndOrderByPurchasers(@Param("asc") boolean asc);

    @Query("SELECT c.courseId FROM CourseEntity c ORDER BY " +
            "CASE WHEN :asc = true THEN size(c.likes) END ASC, " +
            "CASE WHEN :asc = false THEN size(c.likes) END DESC")
    Set<Long> findCourseIdsAndOrderByLikes(@Param("asc") boolean asc);

    @Query("SELECT MIN(c.price) FROM CourseEntity c")
    Double findMinPrice(); // Giá nhỏ nhất

    @Query("SELECT MAX(c.price) FROM CourseEntity c")
    Double findMaxPrice(); // Giá lớn nhất

    CourseEntity findByCourseId(Long courseId);
}
