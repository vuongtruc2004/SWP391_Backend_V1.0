package com.dto.response;

import com.util.enums.DiscountRangeEnum;
import com.util.enums.DiscountTypeEnum;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CouponResponse {

    Long couponId;

    String couponName;

    String couponDescription;

    String couponCode;

    DiscountTypeEnum discountType;

    DiscountRangeEnum discountRange;

    Double discountValue;

    Double maxDiscountAmount;

    Double minOrderValue;

    Long maxUses;

    Long usedCount;

    Instant startTime;

    Instant endTime;

    List<String> courseName;
}
