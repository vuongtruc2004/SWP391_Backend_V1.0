package com.controller.api.v1;

import com.dto.response.CourseResponse;
import com.dto.response.PageDetailsResponse;
import com.entity.CourseEntity;
import com.service.CourseService;
import com.turkraft.springfilter.boot.Filter;
import com.util.annotation.ApiMessage;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @ApiMessage("Lấy các khóa học thành công!")
    @GetMapping("/purchased")
    public ResponseEntity<PageDetailsResponse<List<CourseResponse>>> getCoursesAndSortByPurchased(
            Pageable pageable
    ) {
        return ResponseEntity.ok(courseService.getCoursesAndSortByPurchased(pageable));
    }

    @ApiMessage("Lấy các khóa học thành công!")
    @GetMapping
    public ResponseEntity<PageDetailsResponse<List<CourseResponse>>> getCoursesWithFilter(
            @Filter Specification<CourseEntity> specification,
            Pageable pageable
    ) {
        return ResponseEntity.ok(courseService.getCoursesWithFilter(specification, pageable));
    }

    @GetMapping("/users")
    public ResponseEntity<PageDetailsResponse<List<CourseResponse>>> getAllCoursesOfUser(
            @RequestParam(name = "user_id") Long userId,
            Pageable pageable
    ) {
        return ResponseEntity.ok(courseService.getAllCoursesByUser(userId, pageable));
    }
}
