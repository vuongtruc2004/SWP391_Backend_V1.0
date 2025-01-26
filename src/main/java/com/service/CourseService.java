package com.service;


import com.dto.response.CourseResponse;
import com.dto.response.PageDetailsResponse;
import com.entity.CourseEntity;
import com.entity.UserEntity;
import com.repository.CourseRepository;
import com.util.BuildResponse;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final UserService userService;
    private ModelMapper modelMapper;

    public CourseService(CourseRepository courseRepository, UserService userService, ModelMapper modelMapper) {
        this.courseRepository = courseRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    public PageDetailsResponse<List<CourseResponse>> getAllCoursesByUser(Long id, Pageable pageable) {
        UserEntity user = userService.getUserById(id);
        Page<CourseEntity> page = courseRepository.findAllByPurchasers(user, pageable);
        List<CourseResponse> courseResponseListlist = page.getContent().stream()
                .map(courseEntity -> modelMapper.map(courseEntity, CourseResponse.class))
                .toList();
        return BuildResponse.buildPageDetailsResponse(
                page.getNumber()+1,
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements(),
                courseResponseListlist
        );
    }

}
