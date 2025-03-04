package com.controller.api.v1;

import com.dto.request.PurchaseRequest;
import com.dto.response.OrderResponse;
import com.dto.response.PurchaseResponse;
import com.exception.custom.PurchaseException;
import com.helper.PurchaseServiceHelper;
import com.service.PurchaseService;
import com.util.annotation.ApiMessage;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/api/v1/purchase")
@RequiredArgsConstructor
public class PurchaseController {

    private final PurchaseService paymentService;
    private final PurchaseServiceHelper purchaseServiceHelper;

    @ApiMessage("Tạo phiếu mua hàng thành công!")
    @PostMapping
    public ResponseEntity<PurchaseResponse> createPurchaseRequest(@RequestBody PurchaseRequest purchaseRequest, HttpServletRequest httpServletRequest) {
        String ipAddress = purchaseServiceHelper.getIpAddress(httpServletRequest);
        return ResponseEntity.ok(paymentService.createPurchaseRequest(purchaseRequest, ipAddress));
    }

    @ApiMessage("Kích hoạt đơn hàng thành công!")
    @GetMapping
    public ResponseEntity<OrderResponse> activeOrder(@RequestParam String orderCode) {
        return ResponseEntity.ok(paymentService.activeOrder(orderCode));
    }

    @ApiMessage("Xóa hóa đơn thành công!")
    @DeleteMapping("/{orderId}")
    public ResponseEntity<OrderResponse> deleteOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok(paymentService.deleteOrder(orderId));
    }

    @ApiMessage("Thanh toán đơn hàng thành công!")
    @GetMapping("/vnpay-ipn")
    public String processIpn(@RequestParam Map<String, String> params, Model model) {
        try {
            OrderResponse orderResponse = paymentService.processIpn(params);
            model.addAttribute("txnRef", orderResponse.getOrderCode());
            return "payment-success";
        } catch (PurchaseException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            return "payment-fail";
        }
    }
}
