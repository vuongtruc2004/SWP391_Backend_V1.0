package com.controller.api.v1;

import com.dto.response.*;
import com.dto.response.details.CourseDetailsResponse;
import com.entity.CourseEntity;
import com.service.CourseService;
import com.turkraft.springfilter.boot.Filter;
import com.util.annotation.ApiMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

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
            Pageable pageable,
            @RequestParam(name = "specialSort", required = false) String specialSort,
            @RequestParam(name = "expertIds", required = false) String expertIds,
            @RequestParam(name = "subjectIds", required = false) String subjectIds
    ) {
        return ResponseEntity.ok(courseService.getCoursesWithFilter(specification, pageable, specialSort, expertIds, subjectIds));
    }

    @ApiMessage("Lấy khóa học thành công!")
    @GetMapping("/{courseId}")
    public ResponseEntity<CourseDetailsResponse> getCourseById(@PathVariable(name = "courseId") Long courseId) {
        return ResponseEntity.ok(courseService.getCourseById(courseId));
    }

    @ApiMessage("Lấy các khóa học thành công!")
    @GetMapping("/suggestion")
    public ResponseEntity<List<CourseDetailsResponse>> getSuggestedCourses(
            @RequestParam(name = "courseIds") List<Long> courseIds
    ) {
        return ResponseEntity.ok(courseService.getSuggestedCourses(courseIds));
    }

    @ApiMessage("Lấy các khóa học đã mua thành công!")
    @GetMapping("/user/purchased")
    public ResponseEntity<List<CourseStatusResponse>> getPurchasedCourses() {
        return ResponseEntity.ok(courseService.getPurchasedCourses());
    }

    @ApiMessage("Lấy các khóa học thành công!")
    @GetMapping("/all")
    public ResponseEntity<PageDetailsResponse<List<CourseResponse>>> getCoursesWithFilterRoleAdmin(
            Pageable pageable,
            @Filter Specification<CourseEntity> specification,
            @RequestParam(name = "accepted", required = false) Boolean accepted
    ) {
        return ResponseEntity.ok(courseService.getCoursesWithFilterByAdmin(pageable, specification, accepted));
    }

    @GetMapping("/price-range")
    public ResponseEntity<MinMaxPriceResponse> getRangePrice() {
        return ResponseEntity.ok(courseService.getMaxMinPriceOfCourses());
    }

    @ApiMessage("Xóa khóa học thành công!")
    @DeleteMapping("/delete/{courseId}")
    public ResponseEntity<ApiResponse<String>> deleteCourse(@PathVariable Long courseId) {
        return ResponseEntity.ok(courseService.deleteByCourseId(courseId));
    }

    @PutMapping("/accept/{courseId}")
    public ResponseEntity<ApiResponse<String>> updateCourse(@PathVariable Long courseId) {
        return ResponseEntity.ok(courseService.changeAcceptACourse(courseId));
    }

    @PutMapping("/unaccept/{courseId}")
    public ResponseEntity<ApiResponse<String>> unacceptCourse(@PathVariable Long courseId) {
        return ResponseEntity.ok(courseService.changeUnacceptACourse(courseId));
    }

}
