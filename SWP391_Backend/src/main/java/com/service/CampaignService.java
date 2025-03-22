package com.service;

import com.dto.request.CampaignRequest;
import com.dto.response.ApiResponse;
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
import com.util.enums.DiscountTypeEnum;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CampaignService {

    private final CampaignRepository campaignRepository;
    private final ModelMapper modelMapper;
    private final CourseRepository courseRepository;

    public PageDetailsResponse<List<CampaignResponse>> getAllCampaignsWithFilter(
            Pageable pageable,
            Specification<CampaignEntity> specification
    ) {
        Page<CampaignEntity> page = campaignRepository.findAll(specification, pageable);
        List<CampaignResponse> listCampaignResponse = page
                .getContent()
                .stream()
                .map(entity -> modelMapper.map(entity, CampaignResponse.class))
                .collect(Collectors.toList());
        return BuildResponse.buildPageDetailsResponse(
                page.getNumber() + 1,
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements(),
                listCampaignResponse
        );
    }

    public List<CampaignResponse> getAllCampaigns() {
        List<CampaignEntity> list = campaignRepository.findAll();
        List<CampaignResponse> listResponse = list
                .stream()
                .map(entity -> modelMapper.map(entity, CampaignResponse.class))
                .collect(Collectors.toList());
        return listResponse;
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
        campaignEntity = campaignRepository.save(campaignEntity);
        final CampaignEntity finalCampaignEntity = campaignEntity;
        if (request.getDiscountRange() == DiscountRangeEnum.COURSES) {
            Set<CourseEntity> setCourse = request.getCourseIds()
                    .stream()
                    .map(courseId -> {
                        CourseEntity course = courseRepository.findById(courseId)
                                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy khóa học với ID: " + courseId));

                        course.setCampaign(finalCampaignEntity);
                        return course;
                    })
                    .collect(Collectors.toSet());
            campaignEntity.setCourses(setCourse);
        } else {
            Set<CourseEntity> setCourse = courseRepository.findByCampaignIsNull()
                    .stream()
                    .map(course -> {
                        course.setCampaign(finalCampaignEntity);
                        return course;
                    })
                    .collect(Collectors.toSet());
            if (setCourse.isEmpty()) {
                throw new CampaignException("Không còn khóa học nào chưa được áp dụng chiến dịch. Vui lòng chờ hết chiến dịch. Hoặc hủy bỏ khóa học bạn cần ở những chiến dịch khác!");
            }
            campaignEntity.setCourses(setCourse);

        }

        campaignEntity.setThumbnailUrl(request.getThumbnail());
        campaignEntity = campaignRepository.save(campaignEntity);

        return modelMapper.map(campaignEntity, CampaignResponse.class);
    }

    public ApiResponse<String> deleteCampaign(Long campaignId) {
        Optional<CampaignEntity> optionalCampaign = campaignRepository.findById(campaignId);
        if (optionalCampaign.isPresent()) {
            CampaignEntity campaignEntity = optionalCampaign.get();
            for (CourseEntity course : campaignEntity.getCourses()) {
                if (campaignEntity.getDiscountType().equals(DiscountTypeEnum.FIXED)) {

                    Double oldPrice = course.getPrice() + campaignEntity.getDiscountPercentage();
                    course.setPrice(oldPrice);
                    courseRepository.save(course);
                }
                if (campaignEntity.getDiscountType().equals(DiscountTypeEnum.PERCENTAGE)) {
                    Double oldPrice = course.getPrice() / (1 - (campaignEntity.getDiscountPercentage() / 100.0));
                    course.setPrice(oldPrice);
                    courseRepository.save(course);
                }
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

    @Transactional
    public CampaignResponse updateCampaign(CampaignRequest request) {
        CampaignEntity campaignEntity = campaignRepository.findById(request.getCampaignId())
                .orElseThrow(() -> new NotFoundException("Không tìm thấy chiến dịch!"));

        if (campaignRepository.existsByCampaignNameAndCampaignIdNot(request.getCampaignName(), request.getCampaignId())) {
            throw new CampaignException("Tên chiến dịch đã tồn tại!");
        }

        boolean isCampaignActive = campaignEntity.getStartTime().isBefore(Instant.now())
                && campaignEntity.getEndTime().isAfter(Instant.now());

        // Danh sách các khóa học hiện tại đang thuộc chiến dịch này
        Set<CourseEntity> oldCourses = new HashSet<>(campaignEntity.getCourses());

        // Nếu chiến dịch đang diễn ra, hoàn trả giá gốc cho khóa học cũ trước khi cập nhật chiến dịch
        if (isCampaignActive) {
            for (CourseEntity course : oldCourses) {
                if (campaignEntity.getDiscountType().equals(DiscountTypeEnum.FIXED)) {
                    course.setPrice(course.getPrice() + campaignEntity.getDiscountPercentage());
                } else if (campaignEntity.getDiscountType().equals(DiscountTypeEnum.PERCENTAGE)) {
                    course.setPrice(course.getPrice() / (1 - (campaignEntity.getDiscountPercentage() / 100.0)));
                }
                courseRepository.save(course);
            }
        }

        // Cập nhật thông tin chiến dịch
        modelMapper.map(request, campaignEntity);
        campaignEntity.setDiscountRange(request.getDiscountRange());
        campaignEntity.setThumbnailUrl(request.getThumbnail());
        campaignEntity.setStartTime(DateUtil.parseToInstant(request.getStartTime()));
        campaignEntity.setEndTime(DateUtil.parseToInstant(request.getEndTime()));

        // Danh sách khóa học mới từ request
        Set<Long> newCourseIds = request.getCourseIds() != null ? new HashSet<>(request.getCourseIds()) : Collections.emptySet();

        // Cập nhật chiến dịch cho khóa học mới
        if (!newCourseIds.isEmpty()) {
            List<CourseEntity> allCourses = courseRepository.findAll();
            for (CourseEntity course : allCourses) {
                if (newCourseIds.contains(course.getCourseId())) {
                    course.setCampaign(campaignEntity);

                    if (isCampaignActive) {
                        double newPrice;
                        if (campaignEntity.getDiscountType().equals(DiscountTypeEnum.FIXED)) {
                            newPrice = course.getPrice() - request.getDiscountPercentage();

                            if (newPrice < course.getPrice() * 0.01) {
                                newPrice = course.getPrice() * 0.01;
                            }
                        } else {
                            newPrice = course.getPrice() * (1 - (request.getDiscountPercentage() / 100.0));
                        }

                        course.setPrice(Math.max(newPrice, 1000.0));
                    }

                } else if (oldCourses.contains(course)) {
                    course.setCampaign(null);
                }
            }
            courseRepository.saveAll(allCourses);
        }

        campaignRepository.save(campaignEntity);
        return modelMapper.map(campaignEntity, CampaignResponse.class);
    }

    public MinMaxPriceResponse getMaxMinPriceOfCourses() {
        Double minPrice = campaignRepository.findMinPrice();
        Double maxPrice = campaignRepository.findMaxPrice();
        return new MinMaxPriceResponse(minPrice, maxPrice);
    }


}