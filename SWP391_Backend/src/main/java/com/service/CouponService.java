package com.service;

import com.dto.request.CouponRequest;
import com.dto.response.ApiResponse;
import com.dto.response.CouponResponse;
import com.dto.response.PageDetailsResponse;
import com.entity.CouponEntity;
import com.entity.CourseEntity;
import com.exception.custom.UserException;
import com.helper.CouponServiceHelper;
import com.helper.UserServiceHelper;
import com.repository.CouponRepository;
import com.repository.CourseRepository;
import com.util.BuildResponse;
import com.util.DateUtil;
import com.util.enums.DiscountRangeEnum;
import com.util.enums.DiscountTypeEnum;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CouponService {
    private final CouponRepository couponRepository;
    private final ModelMapper modelMapper;
    private final CourseRepository courseRepository;
    private final CouponServiceHelper couponServiceHelper;
    private final UserServiceHelper userServiceHelper;

    public CouponResponse createCoupon(CouponRequest couponRequest) {
        if (this.couponRepository.existsByCouponCode(couponRequest.getCouponCode().trim())) {
            throw new UserException("Coupon này đã tồn tại!");
        }
        CouponResponse couponResponse = new CouponResponse();
        CouponEntity couponEntity = new CouponEntity();
        Instant startDay = DateUtil.parseToInstant(couponRequest.getStartTime());
        Instant endDay = DateUtil.parseToInstant(couponRequest.getEndTime());
        couponEntity.setStartTime(startDay);
        couponEntity.setEndTime(endDay);
        couponEntity.setCouponCode(couponRequest.getCouponCode().trim());
        couponEntity.setCouponName(couponRequest.getCouponName().trim());
        couponEntity.setCouponDescription(couponRequest.getCouponDescription().trim());
        couponEntity.setMaxDiscountAmount(couponRequest.getMaxDiscountAmount());
        couponEntity.setMaxUses(couponRequest.getMaxUses());
        couponEntity.setMinOrderValue(couponRequest.getMinOrderValue());
        Set<CourseEntity> courseEntities = new HashSet<>();
        List<String> courseName = new ArrayList<>();
        if (couponRequest.getDiscountRange().equals("COURSES")) {
            couponEntity.setDiscountRange(DiscountRangeEnum.COURSES);
            for (String courseEntity : couponRequest.getCourses()) {
                CourseEntity currentCourse = this.courseRepository.findByCourseName(courseEntity);
                courseEntities.add(currentCourse);
                courseName.add(currentCourse.getCourseName());
            }
        } else {
            couponEntity.setDiscountRange(DiscountRangeEnum.ALL);
            for (CourseEntity courseEntity : this.courseRepository.findAll()) {
                courseEntities.add(courseEntity);
                courseName.add(courseEntity.getCourseName());
            }
        }
        if (couponRequest.getDiscountType().equals("FIXED")) {
            couponEntity.setDiscountType(DiscountTypeEnum.FIXED);
            couponEntity.setDiscountAmount(couponRequest.getDiscountValue());
        } else {
            couponEntity.setDiscountType(DiscountTypeEnum.PERCENTAGE);
            couponEntity.setDiscountPercent(couponRequest.getDiscountValue());
        }
        couponEntity.setCourses(courseEntities);
        this.couponRepository.save(couponEntity);
        couponResponse.setCourseName(courseName);
        modelMapper.map(couponEntity, couponResponse);
        return couponResponse;

    }

    public PageDetailsResponse<List<CouponResponse>> getCouponWithFilterAdmin(
            Pageable pageable,
            Specification<CouponEntity> specification
    ) {
        Page<CouponEntity> page = this.couponRepository.findAll(specification, pageable);
        List<CouponResponse> listCouponResponse = couponServiceHelper.convertToCouponResponseList(page.getContent());
        return BuildResponse.buildPageDetailsResponse(
                page.getNumber() + 1,
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements(),
                listCouponResponse
        );
    }

    @Transactional
    public ApiResponse<String> deleteByCouponId(Long couponId) {
        this.couponRepository.deleteById(couponId);
        return BuildResponse.buildApiResponse(
                HttpStatus.OK.value(),
                "Thành công!",
                "Bạn đã xóa coupon có Id là " + couponId + " thành công!",
                null
        );
    }

    @Transactional
    public CouponResponse updateCoupon(CouponRequest couponRequest) {
        CouponResponse couponResponse = new CouponResponse();
        CouponEntity couponEntity = this.couponRepository.findById(couponRequest.getCouponId()).get();
        Instant startDay = DateUtil.parseToInstant(couponRequest.getStartTime());
        Instant endDay = DateUtil.parseToInstant(couponRequest.getEndTime());
        couponEntity.setStartTime(startDay);
        couponEntity.setEndTime(endDay);
        couponEntity.setCouponCode(couponRequest.getCouponCode().trim());
        couponEntity.setCouponName(couponRequest.getCouponName().trim());
        couponEntity.setCouponDescription(couponRequest.getCouponDescription().trim());
        couponEntity.setMaxDiscountAmount(couponRequest.getMaxDiscountAmount());
        couponEntity.setMaxUses(couponRequest.getMaxUses());
        couponEntity.setMinOrderValue(couponRequest.getMinOrderValue());
        Set<CourseEntity> courseEntities = new HashSet<>();
        List<String> courseName = new ArrayList<>();
        if (couponRequest.getDiscountRange().equals("COURSES")) {
            couponEntity.setDiscountRange(DiscountRangeEnum.COURSES);
            for (String courseEntity : couponRequest.getCourses()) {
                CourseEntity currentCourse = this.courseRepository.findByCourseName(courseEntity);
                courseEntities.add(currentCourse);
                courseName.add(currentCourse.getCourseName());
            }
        } else {
            couponEntity.setDiscountRange(DiscountRangeEnum.ALL);
            for (CourseEntity courseEntity : this.courseRepository.findAll()) {
                courseEntities.add(courseEntity);
                courseName.add(courseEntity.getCourseName());
            }
        }
        if (couponRequest.getDiscountType().equals("FIXED")) {
            couponEntity.setDiscountType(DiscountTypeEnum.FIXED);
            couponEntity.setDiscountAmount(couponRequest.getDiscountValue());
        } else {
            couponEntity.setDiscountType(DiscountTypeEnum.PERCENTAGE);
            couponEntity.setDiscountPercent(couponRequest.getDiscountValue());
        }
        couponEntity.setCourses(courseEntities);
        this.couponRepository.save(couponEntity);
        couponResponse.setCourseName(courseName);
        modelMapper.map(couponEntity, couponResponse);
        return couponResponse;
    }

    public List<CouponResponse> getAllCouponsAvailableInACourses(List<Long> courseIds) {
        List<CouponEntity> couponEntityList = couponRepository.findAllCouponsAvailableInACourses(courseIds, Instant.now());
        return couponEntityList.stream().map(couponEntity -> modelMapper.map(couponEntity, CouponResponse.class)).toList();
    }
}
