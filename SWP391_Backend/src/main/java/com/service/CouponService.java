package com.service;

import com.dto.request.CouponRequest;
import com.dto.request.CourseRequest;
import com.dto.response.CouponResponse;
import com.dto.response.PageDetailsResponse;
import com.dto.response.details.CourseDetailsResponse;
import com.entity.CouponEntity;
import com.entity.CourseEntity;
import com.entity.UserEntity;
import com.exception.custom.UserException;
import com.helper.CouponServiceHelper;
import com.repository.CouponRepository;
import com.repository.CourseRepository;
import com.repository.UserRepository;
import com.service.auth.JwtService;
import com.util.BuildResponse;
import com.util.enums.RoleNameEnum;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CouponService {
    private final CouponRepository couponRepository;
    private final ModelMapper modelMapper;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final CouponServiceHelper couponServiceHelper;
    public CouponResponse createCoupon(CouponRequest couponRequest) {
        if(this.couponRepository.existsByCouponCode(couponRequest.getCouponCode().trim())) {
            throw new UserException("Coupon này đã tồn tại!");
        }
        CouponResponse couponResponse = new CouponResponse();
        CouponEntity couponEntity = new CouponEntity();
        modelMapper.map(couponRequest, couponEntity);
        Set<CourseEntity> courseEntities = new HashSet<>();
        List<String> courseName=new ArrayList<>();
        for(CourseEntity courseEntity:couponRequest.getCourses()){
            CourseEntity currentCourse=this.courseRepository.findById(courseEntity.getCourseId()).get();
            courseEntities.add(currentCourse);
            courseName.add(currentCourse.getCourseName());
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
}
