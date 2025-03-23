package com.repository;

import com.entity.CampaignEntity;
import com.repository.custom.JpaSpecificationRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Set;

@Repository
public interface CampaignRepository extends JpaSpecificationRepository<CampaignEntity, Long> {
    boolean existsByCampaignName(String name);

    boolean existsByCampaignNameAndCampaignIdNot(String campaignName, Long campaignId);

    @Query("select MIN(c.discountPercentage) FROM CampaignEntity c")
    Double findMinPrice();

    @Query("select MAX(c.discountPercentage) FROM CampaignEntity c")
    Double findMaxPrice();

    List<CampaignEntity> findAllByEndTimeAfter(Instant end);

    Set<CampaignEntity> findAllByEndTimeLessThanEqual(Instant endTimeIsLessThan);
}