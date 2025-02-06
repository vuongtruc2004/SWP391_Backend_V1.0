package com.service;


import com.dto.response.CourseResponse;
import com.dto.response.PageDetailsResponse;
import com.entity.CourseEntity;
import com.entity.UserEntity;
import com.exception.custom.NotFoundException;
import com.repository.CourseRepository;
import com.repository.UserRepository;
import com.util.BuildResponse;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public CourseService(CourseRepository courseRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    public PageDetailsResponse<List<CourseResponse>> getCoursesAndSortByPurchased(Pageable pageable) {
        Page<CourseEntity> page = courseRepository.findCoursesAndOrderByPurchasersDesc(pageable);
        List<CourseResponse> courseResponses = this.convertToListResponse(page);
        return BuildResponse.buildPageDetailsResponse(
                page.getNumber() + 1,
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements(),
                courseResponses
        );
    }

    public PageDetailsResponse<List<CourseResponse>> getCoursesWithFilter(
            Specification<CourseEntity> specification,
            Pageable pageable
    ) {
        Page<CourseEntity> page = courseRepository.findAll(specification, pageable);
        List<CourseResponse> courseResponses = this.convertToListResponse(page);
        return BuildResponse.buildPageDetailsResponse(
                page.getNumber() + 1,
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements(),
                courseResponses
        );
    }

    public PageDetailsResponse<List<CourseResponse>> getAllCoursesByUser(Long id, Pageable pageable) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Mã người dùng không tồn tại!"));
        Page<CourseEntity> page = courseRepository.findAllByUsers(user, pageable);
        List<CourseResponse> courseResponses = this.convertToListResponse(page);
        return BuildResponse.buildPageDetailsResponse(
                page.getNumber() + 1,
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements(),
                courseResponses
        );
    }

    private List<CourseResponse> convertToListResponse(Page<CourseEntity> page) {
        return page.getContent()
                .stream().map(courseEntity -> {
                    CourseResponse courseResponse = modelMapper.map(courseEntity, CourseResponse.class);
                    courseResponse.setObjectives(courseEntity.getObjectiveList());
                    courseResponse.setTotalPurchased(courseEntity.getUsers().size());
                    courseResponse.setTotalLikes(courseEntity.getLikes().size());
                    courseResponse.setTotalComments(courseEntity.getComments().size());
                    return courseResponse;
                })
                .toList();
    }
}
