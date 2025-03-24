package com.repository;

import com.entity.CouponEntity;
import com.entity.CourseEntity;
import com.entity.OrderEntity;
import com.entity.UserEntity;
import com.repository.custom.JpaSpecificationRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaSpecificationRepository<OrderEntity, Long> {
    // Tổng doanh thu của hôm nay
    @Query("SELECT COALESCE(SUM(o.totalPrice), 0) FROM OrderEntity o " +
            "WHERE FUNCTION('DATE', o.paidAt) = FUNCTION('DATE', CURRENT_DATE)")
    Long getTodayRevenue();

    // Tổng doanh thu của hôm qua
    @Query("SELECT COALESCE(SUM(o.totalPrice), 0) FROM OrderEntity o " +
            "WHERE FUNCTION('DATE', o.paidAt) = :yesterday")
    Long getYesterdayRevenue(@Param("yesterday") LocalDate yesterday);

    // Tổng doanh thu của tuần hiện tại
    @Query("SELECT COALESCE(SUM(o.totalPrice), 0) FROM OrderEntity o " +
            "WHERE YEARWEEK(o.paidAt, 1) = YEARWEEK(CURRENT_DATE, 1)")
    Long getCurrentWeekRevenue();

    // Tổng doanh thu của tháng hiện tại
    @Query("SELECT COALESCE(SUM(o.totalPrice), 0) FROM OrderEntity o " +
            "WHERE YEAR(o.paidAt) = YEAR(CURRENT_DATE) " +
            "AND MONTH(o.paidAt) = MONTH(CURRENT_DATE)")
    Long getCurrentMonthRevenue();

    // Tổng doanh thu của quý hiện tại
    @Query("SELECT COALESCE(SUM(o.totalPrice), 0) FROM OrderEntity o " +
            "WHERE QUARTER(o.paidAt) = QUARTER(CURRENT_DATE) " +
            "AND YEAR(o.paidAt) = YEAR(CURRENT_DATE)")
    Long getCurrentQuarterRevenue();

    // Tổng doanh thu của năm hiện tại
    @Query("SELECT COALESCE(SUM(o.totalPrice), 0) FROM OrderEntity o " +
            "WHERE YEAR(o.paidAt) = YEAR(CURRENT_DATE)")
    Long getCurrentYearRevenue();

    // Tổng số lượng học viên hôm nay (đếm DISTINCT user_id)
    @Query("SELECT COUNT(DISTINCT o.user.userId) FROM OrderEntity o " +
            "WHERE FUNCTION('DATE', o.paidAt) = FUNCTION('DATE', CURRENT_DATE)")
    Long getTodayStudents();

    // Tổng số lượng học viên hôm qua (đếm DISTINCT user_id)
    @Query("SELECT COUNT(DISTINCT o.user.userId) FROM OrderEntity o " +
            "WHERE FUNCTION('DATE', o.paidAt) = :yesterday")
    Long getYesterdayStudents(@Param("yesterday") LocalDate yesterday);

    // Tổng số lượng học viên tuần hiện tại (đếm DISTINCT user_id)
    @Query("SELECT COUNT(DISTINCT o.user.userId) FROM OrderEntity o " +
            "WHERE YEARWEEK(o.paidAt, 1) = YEARWEEK(CURRENT_DATE, 1)")
    Long getCurrentWeekStudents();

    // Tổng số lượng học viên tháng hiện tại (đếm DISTINCT user_id)
    @Query("SELECT COUNT(DISTINCT o.user.userId) FROM OrderEntity o " +
            "WHERE YEAR(o.paidAt) = YEAR(CURRENT_DATE) " +
            "AND MONTH(o.paidAt) = MONTH(CURRENT_DATE)")
    Long getCurrentMonthStudents();

    // Tổng số lượng học viên quý hiện tại (đếm DISTINCT user_id)
    @Query("SELECT COUNT(DISTINCT o.user.userId) FROM OrderEntity o " +
            "WHERE QUARTER(o.paidAt) = QUARTER(CURRENT_DATE) " +
            "AND YEAR(o.paidAt) = YEAR(CURRENT_DATE) " +
            "AND o.paidAt <= CURRENT_DATE")
    Long getCurrentQuarterStudents();

    // Tổng số lượng học viên năm hiện tại (đếm DISTINCT user_id)
    @Query("SELECT COUNT(DISTINCT o.user.userId) FROM OrderEntity o " +
            "WHERE YEAR(o.paidAt) = YEAR(CURRENT_DATE)")
    Long getCurrentYearStudents();

    // Tổng số khóa học đã bán hôm nay (đếm DISTINCT order_id)
    @Query("SELECT COUNT(DISTINCT od.orderDetailsId) FROM OrderEntity o " +
            "JOIN o.orderDetails od " +
            "WHERE FUNCTION('DATE', o.paidAt) = FUNCTION('DATE', CURRENT_DATE)")
    Long getTodayOrders();

    // Tổng số khóa học đã bán hôm qua (đếm DISTINCT order_id)
    @Query("SELECT COUNT(DISTINCT od.orderDetailsId) FROM OrderEntity o " +
            "JOIN o.orderDetails od " +
            "WHERE FUNCTION('DATE', o.paidAt) = :yesterday")
    Long getYesterdayOrders(@Param("yesterday") LocalDate yesterday);

    // Tổng số khóa học đã bán tuần hiện tại (đếm DISTINCT order_id)
    @Query("SELECT COUNT(DISTINCT od.orderDetailsId) FROM OrderEntity o " +
            "JOIN o.orderDetails od " +
            "WHERE YEARWEEK(o.paidAt, 1) = YEARWEEK(CURRENT_DATE, 1)")
    Long getCurrentWeekOrders();

    // Tổng số khóa học đã bán tháng hiện tại (đếm DISTINCT order_id)
    @Query("SELECT COUNT(DISTINCT od.orderDetailsId) FROM OrderEntity o " +
            "JOIN o.orderDetails od " +
            "WHERE MONTH(o.paidAt) = MONTH(CURRENT_DATE) " +
            "AND YEAR(o.paidAt) = YEAR(CURRENT_DATE)")
    Long getCurrentMonthOrders();

    // Tổng số khóa học đã bán quý hiện tại (đếm DISTINCT order_id)
    @Query("SELECT COUNT(DISTINCT od.orderDetailsId) FROM OrderEntity o " +
            "JOIN o.orderDetails od " +
            "WHERE QUARTER(o.paidAt) = QUARTER(CURRENT_DATE) " +
            "AND YEAR(o.paidAt) = YEAR(CURRENT_DATE)")
    Long getCurrentQuarterOrders();

    // Tổng số khóa học đã bán năm hiện tại (đếm DISTINCT order_id)
    @Query("SELECT COUNT(DISTINCT od.orderDetailsId) FROM OrderEntity o " +
            "JOIN o.orderDetails od " +
            "WHERE YEAR(o.paidAt) = YEAR(CURRENT_DATE)")
    Long getCurrentYearOrders();

    @Query("select MIN(o.totalPrice) FROM OrderEntity o")
    Double findMinTotalAmount();

    @Query("SELECT MAX(o.totalPrice) FROM OrderEntity o")
    Double findMaxTotalAmount();

    List<OrderEntity> findByUser_UserId(Long userId);

    @Query("select o from OrderEntity o " +
            "where o.user = :user and (o.expiredAt > :now or o.paidAt is not null) " +
            "order by o.createdAt desc")
    List<OrderEntity> findAllByUserAndExpiredAtAfterOrPaidAtIsNotNullOrderByCreatedAtDesc(@Param("user") UserEntity user, @Param("now") Instant now);

    List<OrderEntity> findAllByUserAndPaidAtIsNullAndExpiredAtAfterOrderByCreatedAtDesc(UserEntity user, Instant expiredAtAfter);

    List<OrderEntity> findAllByUserAndPaidAtIsNullAndExpiredAtLessThanEqualOrderByCreatedAtDesc(UserEntity user, Instant expiredAt);

    List<OrderEntity> findAllByUserAndPaidAtIsNotNullOrderByCreatedAtDesc(UserEntity user);

    Optional<OrderEntity> findByOrderCodeAndPaidAtIsNull(String orderCode);

    Optional<OrderEntity> findByOrderIdAndPaidAtIsNull(Long orderId);

    Optional<OrderEntity> findByCouponAndPaidAtIsNull(CouponEntity coupon);

    @Query("SELECT COUNT(o) > 0 FROM OrderEntity o " +
            "JOIN o.orderDetails od " +
            "WHERE od.course = :course AND o.user = :user")
    boolean existsByCourseAndUser(@Param("course") CourseEntity course, @Param("user") UserEntity user);

    Optional<OrderEntity> findByOrderCode(String orderCode);
}
