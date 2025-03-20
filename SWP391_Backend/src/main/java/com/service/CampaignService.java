package com.service;

import com.dto.request.CampaignRequest;
import com.dto.response.ApiResponse;
import com.dto.response.CampaignResponse;
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

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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
            if(setCourse.isEmpty()) {
                throw new CampaignException("Không còn khóa học nào chưa được áp dụng chiến dịch. Vui lòng chờ hết chiến dịch. Hoặc hủy bỏ khóa học bạn cần ở những chiến dịch khác!");
            }
            campaignEntity.setCourses(setCourse);

        }

        campaignEntity.setThumbnailUrl(request.getThumbnail());
        campaignEntity = campaignRepository.save(campaignEntity);
        System.out.println("Courses in campaign: " + campaignEntity.getCourses()); // Debug

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

    public CampaignResponse updateCampaign(CampaignRequest request) {
        CampaignEntity campaignEntity = campaignRepository.findById(request.getCampaignId()).orElseThrow(() -> new NotFoundException("Không tìm thấy chiến dịch!"));
        if (campaignRepository.existsByCampaignNameAndCampaignIdNot(request.getCampaignName(), request.getCampaignId())) {
            throw new CampaignException("Tên chiến dịch đã tồn tại!");
        }
        if (campaignEntity.getStartTime().isBefore(Instant.now()) && campaignEntity.getEndTime().isAfter(Instant.now())) {

            campaignEntity.getCourses().forEach(course -> {
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
                if (request.getDiscountType().equals(DiscountTypeEnum.FIXED)) {

                    if (course.getPrice() - campaignEntity.getDiscountPercentage() < 0) {
                        course.setPrice(1000.0);
                    } else {
                        Double salePrice = course.getPrice() - request.getDiscountPercentage();
                        course.setPrice(salePrice);
                    }

                    courseRepository.save(course);
                }
                if (request.getDiscountType().equals(DiscountTypeEnum.PERCENTAGE)) {
                    Double salePrice = course.getPrice() - ((course.getPrice() * request.getDiscountPercentage()) / 100);
                    course.setPrice(salePrice);
                    courseRepository.save(course);
                }
            });
        }

        modelMapper.map(request, campaignEntity);

        List<CourseEntity> allCourses = courseRepository.findAll();

        if (request.getDiscountType().equals(DiscountRangeEnum.COURSES)) {
            Set<Long> selectedCourseIds = new HashSet<>(request.getCourseIds());

            for (CourseEntity course : allCourses) {
                if (selectedCourseIds.contains(course.getCourseId())) {
                    course.setCampaign(campaignEntity);
                } else {
                    course.setCampaign(null);
                }
            }
            courseRepository.saveAll(allCourses);
        }

        campaignEntity.setDiscountRange(request.getDiscountRange());
        campaignEntity.setThumbnailUrl(request.getThumbnail());
        campaignEntity.setStartTime(DateUtil.parseToInstant(request.getStartTime()));
        campaignEntity.setEndTime(DateUtil.parseToInstant(request.getEndTime()));
        campaignRepository.save(campaignEntity);
        return modelMapper.map(campaignEntity, CampaignResponse.class);
    }

}