package com.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CouponRequest {

    Long couponId;

    String couponName;

    String couponDescription;

    String couponCode;

    String discountType;

    String discountRange;

    Double discountValue;

    Double maxDiscountAmount;

    Double minOrderValue;

    Long maxUses;

    Long usedCount;

    String startTime;

    String endTime;

    List<String> courses;
}
