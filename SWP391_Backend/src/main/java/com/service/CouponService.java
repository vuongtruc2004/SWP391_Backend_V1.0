package com.service;

import com.dto.request.CouponRequest;
import com.dto.response.CouponResponse;
import com.entity.CouponEntity;
import com.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class CouponService {
    private final CouponRepository couponRepository;
    public CouponResponse createCoupon(CouponRequest couponRequest) {
        CouponResponse couponResponse = new CouponResponse();
        CouponEntity couponEntity = new CouponEntity();
        return couponResponse;

    }
}
