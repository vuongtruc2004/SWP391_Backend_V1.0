package com.repository;

import com.entity.OrderEntity;
import com.repository.custom.JpaSpecificationRepository;
import com.util.enums.OrderStatusEnum;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaSpecificationRepository<OrderEntity, Long> {
    @Query("select o from OrderEntity o " +
            "join o.orderDetails od " +
            "where o.user.userId = :userId and od.courseId in (:courseIds)")
    List<OrderEntity> findByUserIdAndCourseIds(@Param("userId") Long userId, @Param("courseIds") List<Long> courseIds);

    // Tổng doanh thu từ trước đến nay
    @Query("SELECT COALESCE(SUM(od.price), 0) FROM OrderDetailsEntity od " +
            "JOIN od.order o WHERE o.orderStatus = :status")
    Long getTotalRevenue(@Param("status") OrderStatusEnum status);


    // Tổng doanh thu của hôm nay
    @Query("SELECT COALESCE(SUM(od.price), 0) FROM OrderDetailsEntity od " +
            "JOIN od.order o " +
            "WHERE FUNCTION('DATE', o.createdAt) = FUNCTION('DATE', CURRENT_DATE) " +
            "AND o.orderStatus = :status")
    Long getTodayRevenue(@Param("status") OrderStatusEnum status);


    // Tổng doanh thu của hôm qua
    @Query("SELECT COALESCE(SUM(od.price), 0) FROM OrderDetailsEntity od " +
            "JOIN od.order o " +
            "WHERE FUNCTION('DATE', o.createdAt) = :yesterday " +
            "AND o.orderStatus = 'COMPLETED'")
    Long getYesterdayRevenue(@Param("yesterday") LocalDate yesterday);


    // Tổng số lượng học viên từ trước đến nay (đếm DISTINCT user_id)
    @Query("SELECT COUNT(DISTINCT o.user.userId) FROM OrderEntity o " +
            "WHERE o.orderStatus = :status")
    Long getTotalStudents(@Param("status") OrderStatusEnum status);


    // Tổng số lượng học viên hôm nay (đếm DISTINCT user_id)
    @Query("SELECT COUNT(DISTINCT o.user.userId) FROM OrderEntity o " +
            "WHERE FUNCTION('DATE', o.createdAt) = FUNCTION('DATE', CURRENT_DATE) " +
            "AND o.orderStatus = :status")
    Long getTodayStudents(@Param("status") OrderStatusEnum status);


    // Tổng số lượng học viên hôm qua (đếm DISTINCT user_id)
    @Query("SELECT COUNT(DISTINCT o.user.userId) FROM OrderEntity o " +
            "WHERE FUNCTION('DATE', o.createdAt) = :yesterday " +
            "AND o.orderStatus = 'COMPLETED'")
    Long getYesterdayStudents(@Param("yesterday") LocalDate yesterday);


    // Tổng số khóa học đã bán từ trước đến nay (đếm DISTINCT order_id)
    @Query("SELECT COUNT(DISTINCT o.orderId) FROM OrderEntity o " +
            "WHERE o.orderStatus = :status")
    Long getTotalOrders(@Param("status") OrderStatusEnum status);


    // Tổng số khóa học đã bán hôm nay (đếm DISTINCT order_id)
    @Query("SELECT COUNT(DISTINCT o.orderId) FROM OrderEntity o " +
            "WHERE FUNCTION('DATE', o.createdAt) = FUNCTION('DATE', CURRENT_DATE) " +
            "AND o.orderStatus = :status")
    Long getTodayOrders(@Param("status") OrderStatusEnum status);


    // Tổng số khóa học đã bán hôm qua (đếm DISTINCT order_id)
    @Query("SELECT COUNT(DISTINCT o.orderId) FROM OrderEntity o " +
            "WHERE FUNCTION('DATE', o.createdAt) = :yesterday " +
            "AND o.orderStatus = 'COMPLETED'")
    Long getYesterdayOrders(@Param("yesterday") LocalDate yesterday);

    @Query("SELECT o FROM OrderEntity o JOIN o.orderDetails od " +
            "GROUP BY o ORDER BY SUM(od.price) ASC LIMIT 1")
    Optional<OrderEntity> findOrderWithMinTotalPrice();

    @Query("SELECT o FROM OrderEntity o JOIN o.orderDetails od " +
            "GROUP BY o ORDER BY SUM(od.price) DESC LIMIT 1")
    Optional<OrderEntity> findOrderWithMaxTotalPrice();

}
