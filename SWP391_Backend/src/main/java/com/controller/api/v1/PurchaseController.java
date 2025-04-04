package com.controller.api.v1;

import com.dto.request.OrderRequest;
import com.dto.response.ApiResponse;
import com.exception.custom.PurchaseException;
import com.helper.PurchaseServiceHelper;
import com.service.PurchaseService;
import com.util.annotation.ApiMessage;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/api/v1/purchase")
@RequiredArgsConstructor
public class PurchaseController {

    private final PurchaseService paymentService;
    private final PurchaseServiceHelper purchaseServiceHelper;

    @PostMapping
    public ResponseEntity<ApiResponse<String>> createPaymentURL(@RequestBody OrderRequest orderRequest, HttpServletRequest httpServletRequest) {
        String ipAddress = purchaseServiceHelper.getIpAddress(httpServletRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentService.createPaymentURL(orderRequest, ipAddress));
    }

    @ApiMessage("Kích hoạt đơn hàng thành công!")
    @GetMapping
    public ResponseEntity<Void> activeOrder(@RequestParam String orderCode) {
        paymentService.activeOrder(orderCode);
        return ResponseEntity.ok().build();
    }

    @ApiMessage("Xóa hóa đơn thành công!")
    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {
        paymentService.deleteOrder(orderId);
        return ResponseEntity.ok().build();
    }

    @ApiMessage("Thanh toán đơn hàng thành công!")
    @GetMapping("/vnpay-ipn")
    public String processIpn(@RequestParam Map<String, String> params, Model model) {
        try {
            paymentService.processIpn(params);
            return "payment-success";
        } catch (PurchaseException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            return "payment-fail";
        }
    }
}
