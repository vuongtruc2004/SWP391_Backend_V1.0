package com.helper;

import com.dto.response.CouponResponse;
import com.dto.response.CourseResponse;
import com.entity.CouponEntity;
import com.entity.CourseEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CouponServiceHelper {
    private final ModelMapper modelMapper;
    public List<CouponResponse> convertToCouponResponseList(Collection<CouponEntity> collection) {
        return collection.stream().map(couponEntity -> {
            CouponResponse couponResponse = modelMapper.map(couponEntity, CouponResponse.class);
            if(couponEntity.getDiscountAmount()==null || couponEntity.getDiscountAmount()==0){
                couponResponse.setDiscountValue(couponEntity.getDiscountPercent());
            }
            if(couponEntity.getDiscountPercent()==null || couponEntity.getDiscountPercent()==0){
                couponResponse.setDiscountValue(couponEntity.getDiscountAmount());
            }
            return couponResponse;
        }).toList();
    }

}
