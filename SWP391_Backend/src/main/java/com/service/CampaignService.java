package com.service;

import com.dto.request.CampaignRequest;
import com.dto.response.CampaignResponse;
import com.dto.response.MinMaxPriceResponse;
import com.dto.response.PageDetailsResponse;
import com.entity.CampaignEntity;
import com.entity.CourseEntity;
import com.exception.custom.CampaignException;
import com.exception.custom.NotFoundException;
import com.repository.CampaignRepository;
import com.repository.CourseRepository;
import com.util.BuildResponse;
import com.util.DateUtil;
import com.util.enums.DiscountRangeEnum;
import com.util.enums.MessageStatusNotificationEnum;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CampaignService {

    private final CampaignRepository campaignRepository;
    private final ModelMapper modelMapper;
    private final CourseRepository courseRepository;
//    private final RedisService redisService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    public PageDetailsResponse<List<CampaignResponse>> getAllCampaignsWithFilter(Pageable pageable, Specification<CampaignEntity> specification) {
        Page<CampaignEntity> page = campaignRepository.findAll(specification, pageable);
        List<CampaignResponse> listCampaignResponse = page
                .getContent()
                .stream()
                .map(entity -> modelMapper.map(entity, CampaignResponse.class))
                .toList();
        return BuildResponse.buildPageDetailsResponse(
                page.getNumber() + 1,
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements(),
                listCampaignResponse
        );
    }

    public List<CampaignResponse> getAllCampaigns() {
        List<CampaignEntity> list = campaignRepository.findAllByEndTimeAfter(Instant.now());
        return list.stream().map(entity -> modelMapper.map(entity, CampaignResponse.class)).toList();
    }

    public CampaignResponse createCampaign(CampaignRequest request) {
        if (campaignRepository.existsByCampaignName(request.getCampaignName())) {
            throw new CampaignException("Tên chiến dịch đã tồn tại!");
        }

        Instant startTime = DateUtil.parseToInstant(request.getStartTime());
        Instant endTime = DateUtil.parseToInstant(request.getEndTime());

        CampaignEntity campaignEntity = modelMapper.map(request, CampaignEntity.class);
        campaignEntity.setStartTime(startTime);
        campaignEntity.setEndTime(endTime);

        CampaignEntity savedCampaignEntity = campaignRepository.save(campaignEntity);

        setCampaignToCourses(savedCampaignEntity, request);
        //redisService.saveCampaignToRedis(savedCampaignEntity);
        simpMessagingTemplate.convertAndSend("/topic/purchased", MessageStatusNotificationEnum.CAMPAIGN.toString());
        return modelMapper.map(campaignEntity, CampaignResponse.class);
    }

    public void deleteCampaign(Long campaignId) {
        CampaignEntity campaign = campaignRepository.findById(campaignId)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy chiến dịch!"));
        for (CourseEntity course : campaign.getCourses()) {
            course.setCampaign(null);
            courseRepository.save(course);
        }
        campaignRepository.delete(campaign);
        simpMessagingTemplate.convertAndSend("/topic/purchased", MessageStatusNotificationEnum.CAMPAIGN.toString());
        //redisService.removeCampaignFromRedis(campaign);
    }

    @Transactional
    public CampaignResponse updateCampaign(CampaignRequest request) {
        if (request.getCampaignId() == null) {
            throw new NotFoundException("");
        }
        CampaignEntity existedCampaign = campaignRepository.findById(request.getCampaignId())
                .orElseThrow(() -> new NotFoundException(""));

        if (campaignRepository.existsByCampaignNameAndCampaignIdNot(request.getCampaignName(), existedCampaign.getCampaignId())) {
            throw new CampaignException("Tên chiến dịch đã tồn tại!");
        }

        if (request.getStartTime() != null && request.getEndTime() != null) {
            Instant startTime = DateUtil.parseToInstant(request.getStartTime());
            Instant endTime = DateUtil.parseToInstant(request.getEndTime());

            existedCampaign.setStartTime(startTime);
            existedCampaign.setEndTime(endTime);
        }

        existedCampaign.setCampaignName(request.getCampaignName());
        existedCampaign.setThumbnailUrl(request.getThumbnailUrl());
        existedCampaign.setDiscountRange(request.getDiscountRange());
        existedCampaign.setDiscountPercentage(request.getDiscountPercentage());
        existedCampaign.setCampaignDescription(request.getCampaignDescription());

        CampaignEntity savedCampaignEntity = campaignRepository.save(existedCampaign);
        Set<CourseEntity> currentCourses = existedCampaign.getCourses();
        for (CourseEntity course : currentCourses) {
            course.setCampaign(null);
            courseRepository.save(course);
        }

        setCampaignToCourses(savedCampaignEntity, request);
       // redisService.saveCampaignToRedis(savedCampaignEntity);
        simpMessagingTemplate.convertAndSend("/topic/purchased", MessageStatusNotificationEnum.CAMPAIGN.toString());
        return modelMapper.map(savedCampaignEntity, CampaignResponse.class);
    }

    public MinMaxPriceResponse getMaxMinPriceOfCourses() {
        Double minPrice = campaignRepository.findMinPrice();
        Double maxPrice = campaignRepository.findMaxPrice();
        return new MinMaxPriceResponse(minPrice, maxPrice);
    }

    private void setCampaignToCourses(CampaignEntity savedCampaignEntity, CampaignRequest request) {
        Set<CourseEntity> courses = request.getDiscountRange().equals(DiscountRangeEnum.ALL) ? courseRepository.findAllByCampaignIsNull() : courseRepository.findAllByCampaignIsNullAndCourseIdIn(request.getCourseIds());
        if (courses.isEmpty()) {
            throw new CampaignException("Không còn khóa học nào chưa được áp dụng chiến dịch!");
        }
        for (CourseEntity course : courses) {
            course.setCampaign(savedCampaignEntity);
            courseRepository.save(course);
        }
    }

}