package com.dto.response;

import com.entity.CourseEntity;
import com.util.enums.DiscountRangeEnum;
import com.util.enums.DiscountTypeEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.Set;

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

    Double discountPercent;

    Double discountAmount;

    Double maxDiscountAmount;

    Double minOrderValue;


    Long maxUses;

    Long usedCount;

    Instant startTime;

    Instant endTime;

    Set<CourseEntity> courses;

}
