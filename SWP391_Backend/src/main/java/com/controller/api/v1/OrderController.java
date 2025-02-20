package com.controller.api.v1;

import com.dto.request.OrderRequest;
import com.dto.response.OrderResponse;
import com.service.OrderService;
import com.util.annotation.ApiMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
