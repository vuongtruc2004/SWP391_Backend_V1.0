package com.repository;

import com.entity.OrderEntity;
import com.repository.custom.JpaSpecificationRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface OrderRepository extends JpaSpecificationRepository<OrderEntity, Long> {
    // Tổng doanh thu từ trước đến nay
    @Query("SELECT COALESCE(SUM(od.price), 0) FROM OrderDetailsEntity od")
    Long getTotalRevenue();

    // Tổng doanh thu của hôm nay
    @Query("SELECT COALESCE(SUM(od.price), 0) FROM OrderDetailsEntity od WHERE FUNCTION('DATE', od.order.createdAt) = FUNCTION('DATE', CURRENT_DATE)")
    Long getTodayRevenue();

    // Tổng doanh thu của hôm qua
    @Query("SELECT COALESCE(SUM(od.price), 0) FROM OrderDetailsEntity od WHERE FUNCTION('DATE', od.order.createdAt) = :yesterday")
    Long getYesterdayRevenue(@Param("yesterday") LocalDate yesterday);

    // Tổng số lượng học viên từ trước đến nay (đếm DISTINCT user_id)
    @Query("SELECT COUNT(DISTINCT o.user.userId) FROM OrderEntity o")
    Long getTotalStudents();

    // Tổng số lượng học viên hôm nay (đếm DISTINCT user_id)
    @Query("SELECT COUNT(DISTINCT o.user.userId) FROM OrderEntity o WHERE FUNCTION('DATE', o.createdAt) = FUNCTION('DATE', CURRENT_DATE)")
    Long getTodayStudents();

    // Tổng số lượng học viên hôm qua (đếm DISTINCT user_id)
    @Query("SELECT COUNT(DISTINCT o.user.userId) FROM OrderEntity o WHERE FUNCTION('DATE', o.createdAt) = :yesterday")
    Long getYesterdayStudents(@Param("yesterday") LocalDate yesterday);

    // Tổng số khóa học đã bán từ trước đến nay (đếm DISTINCT order_id)
    @Query("SELECT COUNT(DISTINCT o.orderId) FROM OrderEntity o")
    Long getTotalOrders();

    // Tổng số khóa học đã bán hôm nay (đếm DISTINCT order_id)
    @Query("SELECT COUNT(DISTINCT o.orderId) FROM OrderEntity o WHERE FUNCTION('DATE', o.createdAt) = FUNCTION('DATE', CURRENT_DATE)")
    Long getTodayOrders();

    // Tổng số khóa học đã bán hôm qua (đếm DISTINCT order_id)
    @Query("SELECT COUNT(DISTINCT o.orderId) FROM OrderEntity o WHERE FUNCTION('DATE', o.createdAt) = :yesterday")
    Long getYesterdayOrders(@Param("yesterday") LocalDate yesterday);
}
