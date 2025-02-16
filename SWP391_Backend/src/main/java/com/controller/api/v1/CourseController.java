package com.controller.api.v1;

import com.dto.request.CourseRequest;
import com.dto.response.ApiResponse;
import com.dto.response.CourseResponse;
import com.dto.response.MinMaxPriceResponse;
import com.dto.response.PageDetailsResponse;
import com.entity.CourseEntity;
import com.service.CourseService;
import com.turkraft.springfilter.boot.Filter;
import com.util.annotation.ApiMessage;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @GetMapping("/users")
    public ResponseEntity<PageDetailsResponse<List<CourseResponse>>> getAllCoursesOfUser(
            @RequestParam(name = "user_id") Long userId,
            Pageable pageable
    ) {
        return ResponseEntity.ok(courseService.getAllCoursesByUser(userId, pageable));
    }

    @ApiMessage("Lấy các khóa học thành công!")
    @GetMapping("/all")
    public ResponseEntity<PageDetailsResponse<List<CourseResponse>>> getCoursesWithFilterRoleAdmin(
            Pageable pageable,
            @Filter Specification<CourseEntity> specification,
            @RequestParam(name = "accepted", required = false) Boolean accepted
    ) {
        return ResponseEntity.ok(courseService.getCoursesWithFilterRoleAdmin(pageable, specification, accepted));
    }

    @GetMapping("/price-range")
    public ResponseEntity<MinMaxPriceResponse> getRangePrice(){
        return ResponseEntity.ok(courseService.getMaxMinPrice());
    }

    @ApiMessage("Xóa khóa học thành công!")
    @DeleteMapping("/delete/{courseId}")
    public ResponseEntity<ApiResponse<String>> deleteCourse(@PathVariable Long courseId){
        return ResponseEntity.ok(courseService.deleteById(courseId));
    }

    @ApiMessage("Xóa khóa học thành công!")
    @PutMapping("/accept/{courseId}")
    public ResponseEntity<ApiResponse<String>> updateCourse(@PathVariable Long courseId){
        return ResponseEntity.ok(courseService.changeAccept(courseId));
    }

    @ApiMessage("Tạo mới một khoá học")
    @PostMapping
    public ResponseEntity<CourseResponse> createCourse(@RequestBody CourseRequest courseRequest) throws Exception{
        return ResponseEntity.ok().body(this.courseService.createCourse(courseRequest));
    }

    @ApiMessage("Cập nhật một khoá học")
    @PutMapping
    public ResponseEntity<CourseResponse> updateCourse(@RequestBody CourseEntity courseEntity) throws Exception{
        return ResponseEntity.ok().body(this.courseService.updateCourse(courseEntity));
    }


}
