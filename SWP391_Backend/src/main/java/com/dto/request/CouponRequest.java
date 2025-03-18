package com.dto.request;

import com.entity.CourseEntity;
import com.util.enums.DiscountRangeEnum;
import com.util.enums.DiscountTypeEnum;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.List;
import java.util.Set;

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

    Long maxPerUser;

    Double discountValue;

    Double maxDiscountAmount;

    Double minOrderValue;

    Long maxUses;

    Long usedCount;

    String startTime;

    String endTime;
}
