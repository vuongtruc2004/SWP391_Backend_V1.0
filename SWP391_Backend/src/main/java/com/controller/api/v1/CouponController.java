package com.controller.api.v1;


import com.dto.request.CouponRequest;
import com.dto.response.ApiResponse;
import com.dto.response.CouponResponse;
import com.dto.response.PageDetailsResponse;
import com.entity.CouponEntity;
import com.service.CouponService;
import com.turkraft.springfilter.boot.Filter;
import com.util.annotation.ApiMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/coupons")
@RequiredArgsConstructor
public class CouponController {
    private final CouponService couponService;

    @PostMapping
    @ApiMessage("Tạo coupon thành công!")
    public ResponseEntity<CouponResponse> createCoupon(@RequestBody CouponRequest couponRequest) {
        return ResponseEntity.ok().body(this.couponService.createCoupon(couponRequest));
    }

    @GetMapping("/all")
    @ApiMessage("Lấy tất cả coupon thành công!")
    public ResponseEntity<PageDetailsResponse<List<CouponResponse>>> getCouponWithFilterAdmin(
            Pageable pageable,
            @Filter Specification<CouponEntity> specification
    ) {
        return ResponseEntity.ok(couponService.getCouponWithFilterAdmin(pageable, specification));
    }

    @ApiMessage("Xóa coupon thành công!")
    @DeleteMapping("/{couponId}")
    public ResponseEntity<ApiResponse<String>> deleteCoupon(@PathVariable Long couponId) {
        return ResponseEntity.ok(couponService.deleteByCouponId(couponId));
    }

    @ApiMessage("Cập nhật một coupon")
    @PutMapping
    public ResponseEntity<CouponResponse> updateCoupon(@RequestBody CouponRequest courseRequest) throws Exception {
        return ResponseEntity.ok().body(this.couponService.updateCoupon(courseRequest));
    }

    @ApiMessage("Lấy các coupon hợp lệ của 1 khóa học thành công!")
    @GetMapping("/available")
    public ResponseEntity<List<CouponResponse>> getAllCouponsAvailable() {
        return ResponseEntity.ok().body(this.couponService.getAllCouponsAvailable());
    }
}
