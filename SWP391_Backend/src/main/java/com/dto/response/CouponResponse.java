package com.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.util.enums.DiscountTypeEnum;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

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

    Double discountPercent;

    Double discountAmount;

    Double maxDiscountAmount;

    Long maxPerUser;

    Double minOrderValue;

    Long maxUses;

    Long usedCount;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+7")
    Instant startTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+7")
    Instant endTime;
}
