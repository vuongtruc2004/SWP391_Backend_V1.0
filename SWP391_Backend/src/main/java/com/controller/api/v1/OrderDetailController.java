package com.controller.api.v1;

import com.dto.response.ApiResponse;
import com.dto.response.OrderDetailsResponse;
import com.service.OrderDetailService;
import com.util.annotation.ApiMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/orderDetails")
@RequiredArgsConstructor
public class OrderDetailController {

    private final OrderDetailService orderDetailService;

    @ApiMessage("Lấy bản ghi tồn tại courseId truyền vào với userId thành công!")
    @GetMapping("/order_purchased/{courseId}")
    public ResponseEntity<ApiResponse<OrderDetailsResponse>> getCoursePurchased(@PathVariable Long courseId) {
        return ResponseEntity.ok(orderDetailService.getCoursePurchased(courseId));
    }

    @ApiMessage("Lấy tất cả các khóa học đã bán theo thứ tự thành công!")
    @GetMapping("/top-sale")
    public ResponseEntity<List<Map<String, Long>>> getCourseSalesCount() {
        return ResponseEntity.ok(orderDetailService.getCourseSalesCount());
    }
}
