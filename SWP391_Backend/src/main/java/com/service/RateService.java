package com.service;

import com.dto.request.RateRequest;
import com.dto.response.ApiResponse;
import com.dto.response.PageDetailsResponse;
import com.dto.response.RateResponse;
import com.entity.CourseEntity;
import com.entity.RateEntity;
import com.entity.UserEntity;
import com.exception.custom.NotFoundException;
import com.helper.UserServiceHelper;
import com.repository.CourseRepository;
import com.repository.RateRepository;
import com.util.BuildResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RateService {

    private final RateRepository rateRepository;
    private final ModelMapper modelMapper;
    private final UserServiceHelper userServiceHelper;
    private final CourseRepository courseRepository;

    public PageDetailsResponse<List<RateResponse>> getRateWithFilters(
            Specification<RateEntity> specification,
            Pageable pageable
    ) {
        Page<RateEntity> page = rateRepository.findAll(specification, pageable);
        List<RateResponse> rateResponses = page.getContent()
                .stream().map(rateEntity -> modelMapper.map(rateEntity, RateResponse.class)).toList();
        return BuildResponse.buildPageDetailsResponse(
                page.getNumber() + 1,
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements(),
                rateResponses
        );
    }

    public Map<Integer, Integer> getNumberOfCoursesOfEachRate(Long courseId) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 1; i < 6; i++) {
            map.put(i, rateRepository.countRateEntitiesByCourse_CourseIdAndStars(courseId, i));
        }
        return map;
    }


    public ApiResponse<RateResponse> getMyCourseRating(Long courseId) {
        UserEntity user = userServiceHelper.extractUserFromToken();
        Optional<RateEntity> rateOptional = rateRepository.findByCourse_CourseIdAndUser_UserId(courseId, user.getUserId());
        RateEntity rateEntity = null;
        if (rateOptional.isPresent()) {
            rateEntity = rateOptional.get();
        } else {
            return BuildResponse.buildApiResponse(
                    HttpStatus.BAD_REQUEST.value(),
                    "Không tìm thấy đánh giá!",
                    null,
                    null
            );
        }
        return BuildResponse.buildApiResponse(
                HttpStatus.OK.value(),
                "Lấy tất cả đánh giá của tôi về 1 khóa học thành công",
                null,
                modelMapper.map(rateEntity, RateResponse.class)
        );
    }

    public ApiResponse<RateResponse> rateCourse(RateRequest request) {
        UserEntity user = userServiceHelper.extractUserFromToken();
        CourseEntity courseEntity = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new NotFoundException("Không tìm thấy khóa học!"));
        RateEntity rateEntity = new RateEntity();
        rateEntity.setContent(request.getContent());
        rateEntity.setStars(request.getStars());
        rateEntity.setCourse(courseEntity);
        rateEntity.setUser(user);
        rateRepository.save(rateEntity);

        return BuildResponse.buildApiResponse(
                HttpStatus.OK.value(),
                "Đánh giá khóa học thành công",
                null,
                modelMapper.map(rateEntity, RateResponse.class)
        );
    }

    public ApiResponse<String> deleteRating(Long rateId) {
        RateEntity rateEntity = rateRepository.findById(rateId).
                orElseThrow(() -> new NotFoundException("Không tìm thấy Id môn học!"));
        
        rateEntity.setCourse(null);
        rateEntity.setUser(null);
        rateRepository.save(rateEntity);
        rateRepository.delete(rateEntity);
        return BuildResponse.buildApiResponse(
                HttpStatus.OK.value(),
                "Thành công!",
                "Bạn đã xóa đánh giá thành công!",
                null
        );
    }

    public ApiResponse<RateResponse> updateRating(Long rateId, RateRequest request) {
        if (rateRepository.existsById(rateId)) {
            RateEntity rateEntity = rateRepository.findById(rateId).get();
            rateEntity.setContent(request.getContent());
            rateEntity.setStars(request.getStars());
            rateRepository.save(rateEntity);
            return BuildResponse.buildApiResponse(
                    HttpStatus.OK.value(),
                    "Thành công!",
                    "Bạn đã chỉnh sửa đánh giá thành công!",
                    modelMapper.map(rateEntity, RateResponse.class)
            );
        } else {
            throw new NotFoundException("Không tìm thấy Id môn học!");
        }
    }
}
