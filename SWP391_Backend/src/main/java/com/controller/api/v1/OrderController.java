package com.controller.api.v1;

import com.dto.request.OrderRequest;
import com.dto.response.DashboardStatisticsResponse;
import com.dto.response.OrderResponse;
import com.dto.response.PageDetailsResponse;
import com.entity.OrderEntity;
import com.service.OrderService;
import com.turkraft.springfilter.boot.Filter;
import com.util.annotation.ApiMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest orderRequest) {
        return ResponseEntity.ok(orderService.createOrder(orderRequest));
    }

    @ApiMessage("Đã thanh toán hóa đơn thành công!")
    @GetMapping("/active/{orderId}")
    public ResponseEntity<OrderResponse> activeCoursesForUser(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderService.activeCoursesForUser(orderId));
    }
    @ApiMessage("Lấy tất cả bài hóa đơn thành công")
    @GetMapping
    public ResponseEntity<PageDetailsResponse<List<OrderResponse>>> getQuizWithFilter(
            Pageable pageable,
            @Filter Specification<OrderEntity> specification) {
        return ResponseEntity.ok(orderService.getOrdersWithFilters(pageable, specification));
    }

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
}
