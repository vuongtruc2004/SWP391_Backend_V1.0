package com.helper;

import com.dto.response.CouponResponse;
import com.entity.CouponEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CouponServiceHelper {
    private final ModelMapper modelMapper;

    public List<CouponResponse> convertToCouponResponseList(Collection<CouponEntity> collection) {
        return collection.stream().map(couponEntity -> {
            CouponResponse couponResponse = modelMapper.map(couponEntity, CouponResponse.class);
            if (couponEntity.getDiscountAmount() == null || couponEntity.getDiscountAmount() == 0) {
                couponResponse.setDiscountPercent(couponEntity.getDiscountPercent());
            }
            if (couponEntity.getDiscountPercent() == null || couponEntity.getDiscountPercent() == 0) {
                couponResponse.setDiscountAmount(couponEntity.getDiscountAmount());
            }
            return couponResponse;
        }).toList();
    }

}
