package com.controller.api.v1;

import com.dto.request.CourseRequest;
import com.dto.response.ApiResponse;
import com.dto.response.CourseResponse;
import com.dto.response.MinMaxPriceResponse;
import com.dto.response.PageDetailsResponse;
import com.dto.response.details.CourseDetailsResponse;
import com.entity.CourseEntity;
import com.service.CourseService;
import com.turkraft.springfilter.boot.Filter;
import com.util.annotation.ApiMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
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
            @RequestParam(name = "subjectIds", required = false) String subjectIds,
            @RequestParam(name = "event", required = false) String event
    ) {
        return ResponseEntity.ok(courseService.getCoursesWithFilter(specification, pageable, specialSort, expertIds, subjectIds, event));
    }

    @ApiMessage("Lấy khóa học thành công!")
    @GetMapping("/{courseId}")
    public ResponseEntity<CourseDetailsResponse> getCourseById(@PathVariable(name = "courseId") Long courseId) {
        return ResponseEntity.ok(courseService.getCourseById(courseId));
    }

    @ApiMessage("Lấy khóa học đã mua với courseId thành công!")
    @GetMapping("/purchased/{courseId}")
    public ResponseEntity<CourseDetailsResponse> getCoursePurchasedByCourseId(@PathVariable(name = "courseId") Long courseId) {
        return ResponseEntity.ok(courseService.getCoursePurchasedByCourseId(courseId));
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
    public ResponseEntity<List<Long>> getPurchasedCourseIds() {
        return ResponseEntity.ok(courseService.getPurchasedCourseIds());
    }

    @ApiMessage("Lấy các khóa học thành công!")
    @GetMapping("/all")
    public ResponseEntity<PageDetailsResponse<List<CourseDetailsResponse>>> getCoursesWithFilterRoleAdmin(
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

    @PutMapping("/accept-status/{courseId}")
    public ResponseEntity<ApiResponse<String>> changeAcceptStatusOfCourse(@PathVariable Long courseId) {
        return ResponseEntity.ok(courseService.changeAcceptStatusOfCourse(courseId));
    }

    @ApiMessage("Tạo mới một khoá học")
    @PostMapping
    public ResponseEntity<CourseResponse> createCourse(@RequestBody CourseRequest courseRequest) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.courseService.createCourse(courseRequest));
    }

    @ApiMessage("Cập nhật một khoá học")
    @PutMapping
    public ResponseEntity<CourseResponse> updateCourse(@RequestBody CourseRequest courseRequest) throws Exception {
        return ResponseEntity.ok().body(this.courseService.updateCourse(courseRequest));
    }

}
