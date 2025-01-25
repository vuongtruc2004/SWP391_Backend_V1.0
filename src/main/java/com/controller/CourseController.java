package com.controller;

import com.dto.ResultPaginationDTO;
import com.entity.CourseEntity;
import com.service.CourseService;
import com.turkraft.springfilter.boot.Filter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseController {
    private final CourseService courseService;
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }
    @GetMapping("/courses")
    public ResponseEntity<ResultPaginationDTO> getAllCourses(
            @Filter Specification<CourseEntity> specification,
            Pageable pageable
    ) {
        return ResponseEntity.ok().body(this.courseService.findAllCourses(specification,pageable));
    }
}
