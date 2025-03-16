package com.repository;

import com.entity.OrderDetailsEntity;
import com.repository.custom.JpaSpecificationRepository;
import com.util.enums.OrderStatusEnum;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderDetailsRepository extends JpaSpecificationRepository<OrderDetailsEntity, Long> {

    @Query("SELECT od.course.courseId FROM OrderDetailsEntity od")
    List<Long> findAllCourseId();

    Long countByCourse_CourseIdAndOrder_OrderStatus(Long courseId, OrderStatusEnum orderStatus);

    Optional<OrderDetailsEntity> findByOrder_OrderIdAndCourse_CourseId(Long orderId, Long courseId);

}
