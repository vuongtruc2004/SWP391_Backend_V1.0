package com.controller.api.v1;


import com.dto.request.CouponRequest;
import com.dto.response.CouponResponse;
import com.service.CouponService;
import com.util.annotation.ApiMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/coupons")
@RequiredArgsConstructor
public class CouponController {
    private final CouponService couponService;
//    @PostMapping
//    @ApiMessage("Tạo coupon thành công!")
//    public ResponseEntity<CouponResponse> createCoupon(CouponRequest couponRequest) {
//        return ResponseEntity.ok().body();
//    }
}
