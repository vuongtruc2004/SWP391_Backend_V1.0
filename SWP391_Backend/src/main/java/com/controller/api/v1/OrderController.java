package com.controller.api.v1;

import com.dto.request.CreateOrderRequest;
import com.dto.response.CourseResponse;
import com.dto.response.DashboardStatisticsResponse;
import com.dto.response.OrderResponse;
import com.service.OrderService;
import com.util.annotation.ApiMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @ApiMessage("Tạo phiếu mua hàng thành công!")
    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody CreateOrderRequest createOrderRequest) {
        return ResponseEntity.ok(orderService.createOrder(createOrderRequest));
    }
//
//    @ApiMessage("Đã thanh toán hóa đơn thành công!")
//    @GetMapping("/active/{orderId}")
//    public ResponseEntity<OrderResponse> activeCoursesForUser(@PathVariable Long orderId) {
//        return ResponseEntity.ok(orderService.activeCoursesForUser(orderId));
//    }
//
//    @ApiMessage("Lấy tất cả bài hóa đơn thành công")
//    @GetMapping
//    public ResponseEntity<PageDetailsResponse<List<OrderResponse>>> getQuizWithFilter(
//            Pageable pageable,
//            @Filter Specification<OrderEntity> specification) {
//        return ResponseEntity.ok(orderService.getOrdersWithFilters(pageable, specification));
//    }

    @ApiMessage("Lấy số khóa học mua vào ngày trong tuần thành công!")
    @GetMapping("/course_sell_in_week")
    public ResponseEntity<Map<String, Long>> getTotalSaleByCourseWeek(
            @RequestParam String startDate,
            @RequestParam String endDate) {
        LocalDate startOfWeek = LocalDate.parse(startDate);
        LocalDate endOfWeek = LocalDate.parse(endDate);
        return ResponseEntity.ok(orderService.countOrdersOnEachDayOfWeek(startOfWeek, endOfWeek));
    }

    @ApiMessage("Lấy số liệu thống kê thành công!")
    @GetMapping("/dashboard-statistics")
    public ResponseEntity<DashboardStatisticsResponse> getDashboardStatistics() {
        return ResponseEntity.ok(orderService.getDashboardStatistics());
    }

    @ApiMessage("Lấy các khóa học muốn mua thành công!")
    @GetMapping("/course")
    public ResponseEntity<List<CourseResponse>> getCoursesByIds(@RequestParam List<Long> courseIds) {
        return ResponseEntity.ok(orderService.getCoursesByIds(courseIds));
    }
}
