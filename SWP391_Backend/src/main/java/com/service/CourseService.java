package com.service;


import com.dto.response.CourseResponse;
import com.dto.response.PageDetailsResponse;
import com.entity.CourseEntity;
import com.entity.UserEntity;
import com.exception.custom.NotFoundException;
import com.repository.CourseRepository;
import com.repository.UserRepository;
import com.util.BuildResponse;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        List<CourseResponse> courseResponses = page.getContent()
                .stream().map(courseEntity -> {
                    CourseResponse courseResponse = modelMapper.map(courseEntity, CourseResponse.class);
                    courseResponse.setObjectives(courseEntity.getObjectiveList());
                    courseResponse.setTotalPurchased(courseEntity.getUsers().size());
                    courseResponse.setTotalLikes(courseEntity.getLikes().size());
                    courseResponse.setTotalComments(courseEntity.getComments().size());
                    return courseResponse;
                })
                .toList();

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
        List<CourseResponse> courseResponseListlist = page.getContent()
                .stream()
                .map(courseEntity -> modelMapper.map(courseEntity, CourseResponse.class))
                .toList();
        return BuildResponse.buildPageDetailsResponse(
                page.getNumber() + 1,
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements(),
                courseResponseListlist
        );
    }

    public PageDetailsResponse<List<CourseResponse>> getCoursesByNameAndSortByPurchased(Pageable pageable, String name) {
        Page<CourseEntity> page = courseRepository.findAll(pageable);
        String normalizedName = StringUtils.stripAccents(name.toLowerCase());
        Pattern pattern = Pattern.compile("\\b" + Pattern.quote(normalizedName) + "\\b", Pattern.CASE_INSENSITIVE);
        List<CourseResponse> courseResponses = page.getContent()
                .stream()
                .filter(courseEntity -> {
                    String courseName = StringUtils.stripAccents(courseEntity.getCourseName().toLowerCase());
                    Matcher matcher = pattern.matcher(courseName);
                    return matcher.find();
                })
                .map(courseEntity -> {
                    CourseResponse courseResponse = modelMapper.map(courseEntity, CourseResponse.class);
                    courseResponse.setObjectives(courseEntity.getObjectiveList());
                    courseResponse.setTotalPurchased(courseEntity.getUsers().size());
                    courseResponse.setTotalLikes(courseEntity.getLikes().size());
                    courseResponse.setTotalComments(courseEntity.getComments().size());
                    return courseResponse;
                })
                .sorted((c1, c2) -> Integer.compare(c2.getTotalPurchased(), c1.getTotalPurchased()))
                .toList();
        return BuildResponse.buildPageDetailsResponse(
                page.getNumber() + 1,
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements(),
                courseResponses
        );
    }


}
