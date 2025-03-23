package com.configuration;

import com.entity.CampaignEntity;
import com.repository.CampaignRepository;
import com.service.CampaignService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class CampaignsConfig {

    private final CampaignRepository campaignRepository;
    private final CampaignService campaignService;

    @Scheduled(cron = "0 0 0 * * *") // Chạy vào 00:00 mỗi ngày
    @Transactional
    public void deleteAllExpiredCampaigns() {
        Set<CampaignEntity> campaigns = campaignRepository.findAllByEndTimeLessThanEqual(Instant.now());
        for (CampaignEntity campaign : campaigns) {
            campaignService.deleteCampaign(campaign.getCampaignId());
        }
        log.info("Deleted " + campaigns.size() + " expired campaigns");
    }
}
