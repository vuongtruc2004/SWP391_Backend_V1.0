package com.controller.api.v1;

import com.dto.response.CourseResponse;
import com.dto.response.PageDetailsResponse;
import com.service.CourseService;
import com.util.annotation.ApiMessage;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<PageDetailsResponse<List<CourseResponse>>> getCoursesAndSortByPurchased(Pageable pageable) {
        return ResponseEntity.ok(courseService.getCoursesAndSortByPurchased(pageable));
    }

    @ApiMessage("Lấy các khoá học thành công!")
    @GetMapping("/search/{name}")
    public ResponseEntity<PageDetailsResponse<List<CourseResponse>>> getCoursesByName(@PathVariable String name, Pageable pageable) {
        return ResponseEntity.ok(courseService.getCoursesByNameAndSortByPurchased(pageable, name));
    }

    @GetMapping("/users")
    public ResponseEntity<PageDetailsResponse<List<CourseResponse>>> getAllCoursesOfUser(
            @RequestParam(name = "user_id") Long userId,
            Pageable pageable
    ) {
        return ResponseEntity.ok(courseService.getAllCoursesByUser(userId, pageable));
    }
}
