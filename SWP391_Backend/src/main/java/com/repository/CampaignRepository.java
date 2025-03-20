package com.repository;

import com.entity.CampaignEntity;
import com.repository.custom.JpaSpecificationRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface CampaignRepository extends JpaSpecificationRepository<CampaignEntity, Long> {
    boolean existsByCampaignName(String name);
    boolean existsByCampaignNameAndCampaignIdNot(String campaignName, Long campaignId);
    List<CampaignEntity> findByStartTimeBeforeAndEndTimeAfter(Instant start,Instant end);
    List<CampaignEntity> findByEndTimeBefore(Instant end);
}