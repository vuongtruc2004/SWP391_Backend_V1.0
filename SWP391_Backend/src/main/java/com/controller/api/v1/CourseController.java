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
    public ResponseEntity<PageDetailsResponse<List<CourseResponse>>> getCoursesAndSortByPurchased(Pageable pageable) {
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

    @ApiMessage("Lấy khóa học đã được kích hoạt thành công!")
    @GetMapping("/accepted/{courseId}")
    public ResponseEntity<CourseDetailsResponse> getCourseByIdAndApproved(@PathVariable(name = "courseId") Long courseId) {
        return ResponseEntity.ok(courseService.getCourseByIdAndApproved(courseId));
    }

    @ApiMessage("Lấy khóa học thành công!")
    @GetMapping("/{courseId}")
    public ResponseEntity<CourseDetailsResponse> getCourseByIdAdmin(@PathVariable(name = "courseId") Long courseId) {
        return ResponseEntity.ok(courseService.getCourseByIdAdmin(courseId));
    }

    @ApiMessage("Lấy khóa học đã mua với courseId thành công!")
    @GetMapping("/purchased/{courseId}")
    public ResponseEntity<CourseDetailsResponse> getCoursePurchasedByCourseId(@PathVariable(name = "courseId") Long courseId) {
        return ResponseEntity.ok(courseService.getCoursePurchasedByCourseId(courseId));
    }

    @ApiMessage("Lấy các khóa học thành công!")
    @GetMapping("/suggestion")
    public ResponseEntity<List<CourseResponse>> getSuggestedCourses(@RequestParam(name = "courseIds") List<Long> courseIds) {
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
            @Filter Specification<CourseEntity> specification
    ) {
        return ResponseEntity.ok(courseService.getCoursesWithFilterByAdmin(pageable, specification));
    }

    @GetMapping("/price-range")
    public ResponseEntity<MinMaxPriceResponse> getRangePrice() {
        return ResponseEntity.ok(courseService.getMaxMinPriceOfCourses());
    }

    @ApiMessage("Xóa khóa học thành công!")
    @DeleteMapping("/delete/{courseId}")
    public ResponseEntity<ApiResponse<String>> deleteByCourseId(@PathVariable Long courseId) {
        return ResponseEntity.ok(courseService.deleteByCourseId(courseId));
    }

    @ApiMessage("Tạo mới một khoá học")
    @PostMapping
    public ResponseEntity<CourseResponse> createCourse(@RequestBody CourseRequest courseRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.courseService.createCourse(courseRequest));
    }

    @ApiMessage("Cập nhật một khoá học")
    @PutMapping
    public ResponseEntity<CourseResponse> updateCourse(@RequestBody CourseRequest courseRequest) {
        return ResponseEntity.ok().body(this.courseService.updateCourse(courseRequest));
    }

    @ApiMessage("Gửi yêu cầu chấp nhận khóa học thành công!")
    @PatchMapping("/processing/{courseId}")
    public ResponseEntity<Void> sendCourseToAdminForApprove(@PathVariable Long courseId) {
        courseService.sendCourseToAdminForApprove(courseId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get-course/{courseId}")
    public ResponseEntity<CourseDetailsResponse> getCourseDetailsAdmin(@PathVariable Long courseId) {
        return ResponseEntity.ok().body(this.courseService.getCourseDetailsAdmin(courseId));
    }

    @ApiMessage("Lấy các khóa học mới nhất của chuyên gia người dùng theo dõi thành công!")
    @GetMapping("/latest-courses")
    public ResponseEntity<List<CourseResponse>> getLatestCoursesOfFollowingExperts() {
        return ResponseEntity.ok(courseService.getLatestCoursesOfFollowingExperts());
    }

    @ApiMessage("Lấy các khóa học thành công!")
    @GetMapping("/all-inpagination")
    public ResponseEntity<List<CourseResponse>> getAllCoursesAdmin(
    ) {
        return ResponseEntity.ok(courseService.getAllCourse());
    }

    @ApiMessage("Lấy các khóa học không có trong chiến dịch thành công!")
    @GetMapping("/all-notin-campaign")
    public ResponseEntity<List<CourseResponse>> getAllCoursesNotInCampaign() {
        return ResponseEntity.ok(courseService.getAllCoursesNotInCampaign());
    }

    @ApiMessage("Duyệt khoá học thành công !")
    @PostMapping("/approved/{courseId}")
    public ResponseEntity<ApiResponse<Void>> approveCourse(@PathVariable Long courseId) {
        this.courseService.approvedCourse(courseId);
        return ResponseEntity.ok().build();
    }

    @ApiMessage("Từ chối duyệt khoá học thành công !")
    @PostMapping("/rejected/{courseId}")
    public ResponseEntity<ApiResponse<Void>> rejectedCourse(@PathVariable Long courseId) {
        this.courseService.rejectedCourse(courseId);
        return ResponseEntity.ok().build();
    }
}
