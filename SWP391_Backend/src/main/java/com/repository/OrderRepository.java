package com.repository;

import com.entity.OrderEntity;
import com.entity.UserEntity;
import com.repository.custom.JpaSpecificationRepository;
import com.util.enums.OrderStatusEnum;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaSpecificationRepository<OrderEntity, Long> {
    // Tổng doanh thu của hôm nay
    @Query("SELECT COALESCE(SUM(o.totalPrice), 0) FROM OrderEntity o " +
            "WHERE FUNCTION('DATE', o.createdAt) = FUNCTION('DATE', CURRENT_DATE)")
    Long getTodayRevenue(@Param("status") OrderStatusEnum status);

    // Tổng doanh thu của hôm qua
    @Query("SELECT COALESCE(SUM(o.totalPrice), 0) FROM OrderEntity o " +
            "WHERE FUNCTION('DATE', o.createdAt) = :yesterday")
    Long getYesterdayRevenue(@Param("yesterday") LocalDate yesterday, @Param("status") OrderStatusEnum status);

    // Tổng doanh thu của tuần hiện tại
    @Query("SELECT COALESCE(SUM(o.totalPrice), 0) FROM OrderEntity o " +
            "WHERE YEARWEEK(o.createdAt, 1) = YEARWEEK(CURRENT_DATE, 1)")
    Long getCurrentWeekRevenue(@Param("status") OrderStatusEnum status);

    // Tổng doanh thu của tháng hiện tại
    @Query("SELECT COALESCE(SUM(o.totalPrice), 0) FROM OrderEntity o " +
            "WHERE YEAR(o.createdAt) = YEAR(CURRENT_DATE) " +
            "AND MONTH(o.createdAt) = MONTH(CURRENT_DATE)")
    Long getCurrentMonthRevenue(@Param("status") OrderStatusEnum status);

    // Tổng doanh thu của quý hiện tại
    @Query("SELECT COALESCE(SUM(o.totalPrice), 0) FROM OrderEntity o " +
            "WHERE QUARTER(o.createdAt) = QUARTER(CURRENT_DATE) " +
            "AND YEAR(o.createdAt) = YEAR(CURRENT_DATE)")
    Long getCurrentQuarterRevenue(@Param("status") OrderStatusEnum status);

    // Tổng doanh thu của năm hiện tại
    @Query("SELECT COALESCE(SUM(o.totalPrice), 0) FROM OrderEntity o " +
            "WHERE YEAR(o.createdAt) = YEAR(CURRENT_DATE)")
    Long getCurrentYearRevenue(@Param("status") OrderStatusEnum status);

    // Tổng số lượng học viên hôm nay (đếm DISTINCT user_id)
    @Query("SELECT COUNT(DISTINCT o.user.userId) FROM OrderEntity o " +
            "WHERE FUNCTION('DATE', o.createdAt) = FUNCTION('DATE', CURRENT_DATE)")
    Long getTodayStudents(@Param("status") OrderStatusEnum status);

    // Tổng số lượng học viên hôm qua (đếm DISTINCT user_id)
    @Query("SELECT COUNT(DISTINCT o.user.userId) FROM OrderEntity o " +
            "WHERE FUNCTION('DATE', o.createdAt) = :yesterday")
    Long getYesterdayStudents(@Param("yesterday") LocalDate yesterday, @Param("status") OrderStatusEnum status);

    // Tổng số lượng học viên tuần hiện tại (đếm DISTINCT user_id)
    @Query("SELECT COUNT(DISTINCT o.user.userId) FROM OrderEntity o " +
            "WHERE YEARWEEK(o.createdAt, 1) = YEARWEEK(CURRENT_DATE, 1)")
    Long getCurrentWeekStudents(@Param("status") OrderStatusEnum status);

    // Tổng số lượng học viên tháng hiện tại (đếm DISTINCT user_id)
    @Query("SELECT COUNT(DISTINCT o.user.userId) FROM OrderEntity o " +
            "WHERE YEAR(o.createdAt) = YEAR(CURRENT_DATE) " +
            "AND MONTH(o.createdAt) = MONTH(CURRENT_DATE)")
    Long getCurrentMonthStudents(@Param("status") OrderStatusEnum status);

    // Tổng số lượng học viên quý hiện tại (đếm DISTINCT user_id)
    @Query("SELECT COUNT(DISTINCT o.user.userId) FROM OrderEntity o " +
            "WHERE QUARTER(o.createdAt) = QUARTER(CURRENT_DATE) " +
            "AND YEAR(o.createdAt) = YEAR(CURRENT_DATE) " +
            "AND o.createdAt <= CURRENT_DATE")
    Long getCurrentQuarterStudents(@Param("status") OrderStatusEnum status);

    // Tổng số lượng học viên năm hiện tại (đếm DISTINCT user_id)
    @Query("SELECT COUNT(DISTINCT o.user.userId) FROM OrderEntity o " +
            "WHERE YEAR(o.createdAt) = YEAR(CURRENT_DATE)")
    Long getCurrentYearStudents(@Param("status") OrderStatusEnum status);

    // Tổng số khóa học đã bán hôm nay (đếm DISTINCT order_id)
    @Query("SELECT COUNT(DISTINCT od.orderDetailsId) FROM OrderEntity o " +
            "JOIN o.orderDetails od " +
            "WHERE FUNCTION('DATE', o.createdAt) = FUNCTION('DATE', CURRENT_DATE)")
    Long getTodayOrders(@Param("status") OrderStatusEnum status);

    // Tổng số khóa học đã bán hôm qua (đếm DISTINCT order_id)
    @Query("SELECT COUNT(DISTINCT od.orderDetailsId) FROM OrderEntity o " +
            "JOIN o.orderDetails od " +
            "WHERE FUNCTION('DATE', o.createdAt) = :yesterday")
    Long getYesterdayOrders(@Param("yesterday") LocalDate yesterday, @Param("status") OrderStatusEnum status);

    // Tổng số khóa học đã bán tuần hiện tại (đếm DISTINCT order_id)
    @Query("SELECT COUNT(DISTINCT od.orderDetailsId) FROM OrderEntity o " +
            "JOIN o.orderDetails od " +
            "WHERE YEARWEEK(o.createdAt, 1) = YEARWEEK(CURRENT_DATE, 1)")
    Long getCurrentWeekOrders(@Param("status") OrderStatusEnum status);

    // Tổng số khóa học đã bán tháng hiện tại (đếm DISTINCT order_id)
    @Query("SELECT COUNT(DISTINCT od.orderDetailsId) FROM OrderEntity o " +
            "JOIN o.orderDetails od " +
            "WHERE MONTH(o.createdAt) = MONTH(CURRENT_DATE) " +
            "AND YEAR(o.createdAt) = YEAR(CURRENT_DATE)")
    Long getCurrentMonthOrders(@Param("status") OrderStatusEnum status);

    // Tổng số khóa học đã bán quý hiện tại (đếm DISTINCT order_id)
    @Query("SELECT COUNT(DISTINCT od.orderDetailsId) FROM OrderEntity o " +
            "JOIN o.orderDetails od " +
            "WHERE QUARTER(o.createdAt) = QUARTER(CURRENT_DATE) " +
            "AND YEAR(o.createdAt) = YEAR(CURRENT_DATE)")
    Long getCurrentQuarterOrders(@Param("status") OrderStatusEnum status);

    // Tổng số khóa học đã bán năm hiện tại (đếm DISTINCT order_id)
    @Query("SELECT COUNT(DISTINCT od.orderDetailsId) FROM OrderEntity o " +
            "JOIN o.orderDetails od " +
            "WHERE YEAR(o.createdAt) = YEAR(CURRENT_DATE)")
    Long getCurrentYearOrders(@Param("status") OrderStatusEnum status);

    @Query("select MIN(o.totalPrice) FROM OrderEntity o")
    Double findMinTotalAmount();

    @Query("SELECT MAX(o.totalPrice) FROM OrderEntity o")
    Double findMaxTotalAmount();

    List<OrderEntity> findByUser_UserId(Long userId);

    List<OrderEntity> findAllByUserAndExpiredAtAfterOrPaidAtIsNotNullOrderByCreatedAtDesc(UserEntity user, Instant expiredAtAfter);

    List<OrderEntity> findAllByUserAndPaidAtIsNullAndExpiredAtAfterOrderByCreatedAtDesc(UserEntity user, Instant expiredAtAfter);

    List<OrderEntity> findAllByUserAndPaidAtIsNullAndExpiredAtLessThanEqualOrderByCreatedAtDesc(UserEntity user, Instant expiredAt);

    List<OrderEntity> findAllByUserAndPaidAtIsNotNullOrderByCreatedAtDesc(UserEntity user);

    List<OrderEntity> findAllByPaidAtIsNullAndExpiredAtLessThanEqual(Instant expiredAt);
    
    Optional<OrderEntity> findByOrderCodeAndPaidAtIsNull(String orderCode);

    Optional<OrderEntity> findByOrderIdAndPaidAtIsNull(Long orderId);
}
