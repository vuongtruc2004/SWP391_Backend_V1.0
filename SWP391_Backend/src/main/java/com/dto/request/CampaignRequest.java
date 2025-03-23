package com.dto.request;

import com.util.enums.DiscountRangeEnum;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CampaignRequest {

    Long campaignId;

    String campaignName;

    String campaignDescription;

    String thumbnailUrl;

    Double discountPercentage;

    DiscountRangeEnum discountRange;

    String startTime;

    String endTime;

    Set<Long> courseIds;
}