package com.dto.request;

import com.util.enums.DiscountRangeEnum;
import com.util.enums.DiscountTypeEnum;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.Set;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CampaignRequest {

    Long campaignId;

    String campaignName;

    String campaignDescription;

    String thumbnail;

    Double discountPercentage;

    DiscountTypeEnum discountType;

    DiscountRangeEnum discountRange;

    String startTime;

    String endTime;

    Set<Long> courseIds;

}