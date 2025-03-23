package com.dto.response;

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
public class CampaignResponse {

    Long campaignId;

    String campaignName;

    String campaignDescription;

    String thumbnail;

    Double discountPercentage;

    DiscountRangeEnum discountRange;

    Instant startTime;

    Instant endTime;

    Instant createdAt;

    Instant updatedAt;

}