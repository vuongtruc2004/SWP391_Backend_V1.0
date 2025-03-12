package com.repository;

import com.entity.OrderDetailsEntity;
import com.repository.custom.JpaSpecificationRepository;
import com.util.enums.OrderStatusEnum;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderDetailsRepository extends JpaSpecificationRepository<OrderDetailsEntity, Long> {
    Long countByOrder_OrderStatus(OrderStatusEnum orderStatus);
    @Query("SELECT od.courseId FROM OrderDetailsEntity od")
    List<Long> findAllCourseId();
    Long countByCourseIdAndOrder_OrderStatus(Long courseId, OrderStatusEnum orderStatus);
    Optional<OrderDetailsEntity> findByOrder_OrderIdAndCourseId(Long orderId, Long courseId);

}
