package com.configuration;

import com.dto.response.ApiResponse;
import com.entity.CampaignEntity;
import com.entity.CourseEntity;
import com.exception.custom.CampaignException;
import com.repository.CampaignRepository;
import com.repository.CourseRepository;
import com.util.BuildResponse;
import com.util.enums.DiscountTypeEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class CampaignsConfig {

    private final SimpMessagingTemplate messagingTemplate;
    private final CampaignRepository campaignRepository;
    private final CourseRepository courseRepository;
    private final Set<Long> processedCampaigns = new HashSet<>();

    @Async
//    @Scheduled(fixedRate = 3000)
    @Transactional
    public void saleChangePrice() {
        Instant now = Instant.now();
        List<CampaignEntity> activeCampaigns = campaignRepository.findByStartTimeBeforeAndEndTimeAfter(now, now);
        for (CampaignEntity campaign : activeCampaigns) {
            if (!processedCampaigns.contains(campaign.getCampaignId())) {
                applyDiscount(campaign);
                processedCampaigns.add(campaign.getCampaignId());
            }
        }
    }

    @Async
//    @Scheduled(fixedRate = 3000)
    @Transactional
    public void recoverChangePrice() {
        Instant now = Instant.now();
        List<CampaignEntity> activeCampaigns = campaignRepository.findByEndTimeBefore(now);
        for (CampaignEntity campaign : activeCampaigns) {
            for (CourseEntity course : campaign.getCourses()) {
                CourseEntity courseEntity = courseRepository.findById(course.getCourseId()).orElse(null);
                if (campaign.getDiscountType().equals(DiscountTypeEnum.FIXED)) {
                    if ((course.getPrice() * 100) <= campaign.getDiscountPercentage()) {
                        course.setPrice(course.getPrice() * 100);
                    } else {
                        course.setPrice(course.getPrice() + campaign.getDiscountPercentage());
                    }
                    courseRepository.save(course);
                }
                if (campaign.getDiscountType().equals(DiscountTypeEnum.PERCENTAGE)) {
                    Double oldPrice = courseEntity.getPrice() / (1 - (campaign.getDiscountPercentage() / 100.0));
                    course.setPrice(Math.ceil(oldPrice));
                    courseRepository.save(course);
                }
            }
            this.deleteCampaignNoRecoveryPrice(campaign.getCampaignId());
            this.messagingTemplate.convertAndSend("/topic/campaigns", "Deleted");
        }
    }

    private void applyDiscount(CampaignEntity campaign) {
        for (CourseEntity course : campaign.getCourses()) {
            if (course != null) {
                if (campaign.getDiscountType().equals(DiscountTypeEnum.FIXED)) {
                    if (course.getPrice() - campaign.getDiscountPercentage() < 0) {
                        course.setPrice((course.getPrice() / 100));
                    } else {
                        course.setPrice(course.getPrice() - campaign.getDiscountPercentage());
                    }
                    courseRepository.save(course);
                }
                if (campaign.getDiscountType().equals(DiscountTypeEnum.PERCENTAGE)) {
                    course.setPrice(course.getPrice() - ((course.getPrice() * campaign.getDiscountPercentage()) / 100));
                    courseRepository.save(course);
                }

            }
        }
    }

    private ApiResponse<String> deleteCampaignNoRecoveryPrice(Long campaignId) {
        Optional<CampaignEntity> optionalCampaign = campaignRepository.findById(campaignId);
        if (optionalCampaign.isPresent()) {
            CampaignEntity campaignEntity = optionalCampaign.get();
            for (CourseEntity course : campaignEntity.getCourses()) {
                course.setCampaign(null);
                courseRepository.save(course);
            }
            campaignRepository.deleteById(campaignId);
            return BuildResponse.buildApiResponse(
                    HttpStatus.OK.value(),
                    "Thành công!",
                    null,
                    "Đã xóa chiến dịch thành công!"
            );
        } else {
            throw new CampaignException("Không tìm thấy chiến dịch để xóa!");
        }
    }
}
