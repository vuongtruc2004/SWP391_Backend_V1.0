package com.repository;

import com.entity.CourseEntity;
import com.entity.OrderDetailsEntity;
import com.repository.custom.JpaSpecificationRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderDetailsRepository extends JpaSpecificationRepository<OrderDetailsEntity, Long> {

    @Query("SELECT od.course.courseId FROM OrderDetailsEntity od")
    List<Long> findAllCourseId();

    Optional<OrderDetailsEntity> findByOrder_OrderIdAndCourse_CourseId(Long orderId, Long courseId);

    @Query("SELECT c.courseName, COUNT(o) " +
            "FROM CourseEntity c " +
            "LEFT JOIN OrderDetailsEntity o ON c.courseId = o.course.courseId " +
            "GROUP BY c.courseName " +
            "ORDER BY COUNT(o) DESC")
    List<Object[]> getCourseSalesCount();

    List<OrderDetailsEntity> findAllByCourse(CourseEntity course);
}
