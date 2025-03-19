package com.repository;

import com.entity.OrderEntity;
import com.repository.custom.JpaSpecificationRepository;
import com.util.enums.OrderStatusEnum;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaSpecificationRepository<OrderEntity, Long> {
    @Query("""
            SELECT CASE WHEN COUNT(o) > 0 THEN TRUE ELSE FALSE END 
            FROM OrderEntity o 
            JOIN o.orderDetails od 
            WHERE o.user.userId = :userId 
            AND od.course.courseId in (:courseIds)
            AND o.orderStatus = 'COMPLETED' or o.orderStatus = 'PENDING'
            """)
    boolean existsCompletedAndPendingOrder(@Param("userId") Long userId, @Param("courseIds") List<Long> courseIds);

    List<OrderEntity> findAllByOrderStatusNotAndExpiredAtLessThan(OrderStatusEnum orderStatus, Instant expiredAtIsLessThan);

    List<OrderEntity> findAllByOrderCode(String orderCode);

    // Tổng doanh thu của hôm nay
    @Query("SELECT COALESCE(SUM(o.totalAmount), 0) FROM OrderEntity o " +
            "WHERE FUNCTION('DATE', o.createdAt) = FUNCTION('DATE', CURRENT_DATE) " +
            "AND o.orderStatus = :status")
    Long getTodayRevenue(@Param("status") OrderStatusEnum status);

    // Tổng doanh thu của hôm qua
    @Query("SELECT COALESCE(SUM(o.totalAmount), 0) FROM OrderEntity o " +
            "WHERE FUNCTION('DATE', o.createdAt) = :yesterday " +
            "AND o.orderStatus = :status")
    Long getYesterdayRevenue(@Param("yesterday") LocalDate yesterday, @Param("status") OrderStatusEnum status);


    // Tổng doanh thu của tuần hiện tại
    @Query("SELECT COALESCE(SUM(o.totalAmount), 0) FROM OrderEntity o " +
            "WHERE YEARWEEK(o.createdAt, 1) = YEARWEEK(CURRENT_DATE, 1) " +
            "AND o.orderStatus = :status")
    Long getCurrentWeekRevenue(@Param("status") OrderStatusEnum status);


    // Tổng doanh thu của tháng hiện tại
    @Query("SELECT COALESCE(SUM(o.totalAmount), 0) FROM OrderEntity o " +
            "WHERE YEAR(o.createdAt) = YEAR(CURRENT_DATE) " +
            "AND MONTH(o.createdAt) = MONTH(CURRENT_DATE) " +
            "AND o.orderStatus = :status")
    Long getCurrentMonthRevenue(@Param("status") OrderStatusEnum status);

    // Tổng doanh thu của quý hiện tại
    @Query("SELECT COALESCE(SUM(o.totalAmount), 0) FROM OrderEntity o " +
            "WHERE QUARTER(o.createdAt) = QUARTER(CURRENT_DATE) " +
            "AND YEAR(o.createdAt) = YEAR(CURRENT_DATE) " +
            "AND o.orderStatus = :status")
    Long getCurrentQuarterRevenue(@Param("status") OrderStatusEnum status);

    // Tổng doanh thu của năm hiện tại
    @Query("SELECT COALESCE(SUM(o.totalAmount), 0) FROM OrderEntity o " +
            "WHERE YEAR(o.createdAt) = YEAR(CURRENT_DATE) " +
            "AND o.orderStatus = :status")
    Long getCurrentYearRevenue(@Param("status") OrderStatusEnum status);

    // Tổng số lượng học viên hôm nay (đếm DISTINCT user_id)
    @Query("SELECT COUNT(DISTINCT o.user.userId) FROM OrderEntity o " +
            "WHERE FUNCTION('DATE', o.createdAt) = FUNCTION('DATE', CURRENT_DATE) " +
            "AND o.orderStatus = :status")
    Long getTodayStudents(@Param("status") OrderStatusEnum status);

    // Tổng số lượng học viên hôm qua (đếm DISTINCT user_id)
    @Query("SELECT COUNT(DISTINCT o.user.userId) FROM OrderEntity o " +
            "WHERE FUNCTION('DATE', o.createdAt) = :yesterday " +
            "AND o.orderStatus = :status")
    Long getYesterdayStudents(@Param("yesterday") LocalDate yesterday, @Param("status") OrderStatusEnum status);

    // Tổng số lượng học viên tuần hiện tại (đếm DISTINCT user_id)
    @Query("SELECT COUNT(DISTINCT o.user.userId) FROM OrderEntity o " +
            "WHERE YEARWEEK(o.createdAt, 1) = YEARWEEK(CURRENT_DATE, 1) " +
            "AND o.orderStatus = :status")
    Long getCurrentWeekStudents(@Param("status") OrderStatusEnum status);

    // Tổng số lượng học viên tháng hiện tại (đếm DISTINCT user_id)
    @Query("SELECT COUNT(DISTINCT o.user.userId) FROM OrderEntity o " +
            "WHERE YEAR(o.createdAt) = YEAR(CURRENT_DATE) " +
            "AND MONTH(o.createdAt) = MONTH(CURRENT_DATE) " +
            "AND o.orderStatus = :status")
    Long getCurrentMonthStudents(@Param("status") OrderStatusEnum status);

    // Tổng số lượng học viên quý hiện tại (đếm DISTINCT user_id)
    @Query("SELECT COUNT(DISTINCT o.user.userId) FROM OrderEntity o " +
            "WHERE QUARTER(o.createdAt) = QUARTER(CURRENT_DATE) " +
            "AND YEAR(o.createdAt) = YEAR(CURRENT_DATE) " +
            "AND o.createdAt <= CURRENT_DATE " +
            "AND o.orderStatus = :status")
    Long getCurrentQuarterStudents(@Param("status") OrderStatusEnum status);

    // Tổng số lượng học viên năm hiện tại (đếm DISTINCT user_id)
    @Query("SELECT COUNT(DISTINCT o.user.userId) FROM OrderEntity o " +
            "WHERE YEAR(o.createdAt) = YEAR(CURRENT_DATE) " +
            "AND o.orderStatus = :status")
    Long getCurrentYearStudents(@Param("status") OrderStatusEnum status);

    // Tổng số khóa học đã bán hôm nay (đếm DISTINCT order_id)
    @Query("SELECT COUNT(DISTINCT od.orderDetailsId) FROM OrderEntity o " +
            "JOIN o.orderDetails od " +
            "WHERE FUNCTION('DATE', o.createdAt) = FUNCTION('DATE', CURRENT_DATE) " +
            "AND o.orderStatus = :status")
    Long getTodayOrders(@Param("status") OrderStatusEnum status);

    // Tổng số khóa học đã bán hôm qua (đếm DISTINCT order_id)
    @Query("SELECT COUNT(DISTINCT od.orderDetailsId) FROM OrderEntity o " +
            "JOIN o.orderDetails od " +
            "WHERE FUNCTION('DATE', o.createdAt) = :yesterday " +
            "AND o.orderStatus = :status")
    Long getYesterdayOrders(@Param("yesterday") LocalDate yesterday, @Param("status") OrderStatusEnum status);

    // Tổng số khóa học đã bán tuần hiện tại (đếm DISTINCT order_id)
    @Query("SELECT COUNT(DISTINCT od.orderDetailsId) FROM OrderEntity o " +
            "JOIN o.orderDetails od " +
            "WHERE YEARWEEK(o.createdAt, 1) = YEARWEEK(CURRENT_DATE, 1) " +
            "AND o.orderStatus = :status")
    Long getCurrentWeekOrders(@Param("status") OrderStatusEnum status);

    // Tổng số khóa học đã bán tháng hiện tại (đếm DISTINCT order_id)
    @Query("SELECT COUNT(DISTINCT od.orderDetailsId) FROM OrderEntity o " +
            "JOIN o.orderDetails od " +
            "WHERE MONTH(o.createdAt) = MONTH(CURRENT_DATE) " +
            "AND YEAR(o.createdAt) = YEAR(CURRENT_DATE) " +
            "AND o.orderStatus = :status")
    Long getCurrentMonthOrders(@Param("status") OrderStatusEnum status);

    // Tổng số khóa học đã bán quý hiện tại (đếm DISTINCT order_id)
    @Query("SELECT COUNT(DISTINCT od.orderDetailsId) FROM OrderEntity o " +
            "JOIN o.orderDetails od " +
            "WHERE QUARTER(o.createdAt) = QUARTER(CURRENT_DATE) " +
            "AND YEAR(o.createdAt) = YEAR(CURRENT_DATE) " +
            "AND o.orderStatus = :status")
    Long getCurrentQuarterOrders(@Param("status") OrderStatusEnum status);

    // Tổng số khóa học đã bán năm hiện tại (đếm DISTINCT order_id)
    @Query("SELECT COUNT(DISTINCT od.orderDetailsId) FROM OrderEntity o " +
            "JOIN o.orderDetails od " +
            "WHERE YEAR(o.createdAt) = YEAR(CURRENT_DATE) " +
            "AND o.orderStatus = :status")
    Long getCurrentYearOrders(@Param("status") OrderStatusEnum status);


    @Query("select MIN(o.totalAmount) FROM OrderEntity o")
    Double findMinTotalAmount();


    @Query("SELECT MAX(o.totalAmount) FROM OrderEntity o")
    Double findMaxTotalAmount();

    Optional<OrderEntity> findByOrderIdAndOrderStatusNot(Long orderId, OrderStatusEnum orderStatus);

    List<OrderEntity> findByUser_UserId(Long userId);

    List<OrderEntity> findByUser_UserIdOrderByCreatedAtDesc(Long userId);

    List<OrderEntity> findAllByUser_UserIdAndOrderStatusOrderByCreatedAtDesc(Long userUserId, OrderStatusEnum orderStatus);
}
