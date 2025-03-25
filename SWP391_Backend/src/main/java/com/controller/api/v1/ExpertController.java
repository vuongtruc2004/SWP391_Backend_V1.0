package com.controller.api.v1;

import com.dto.response.ExpertResponse;
import com.dto.response.PageDetailsResponse;
import com.dto.response.details.CourseDetailsResponse;
import com.dto.response.details.ExpertDetailsResponse;
import com.entity.ExpertEntity;
import com.service.ExpertService;
import com.turkraft.springfilter.boot.Filter;
import com.util.annotation.ApiMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/experts")
@RequiredArgsConstructor
public class ExpertController {

    private final ExpertService expertService;

    @ApiMessage("Lấy tất cả chuyên gia thành công !")
    @GetMapping("/course")
    public ResponseEntity<PageDetailsResponse<List<ExpertResponse>>> getExpertsHaveCourses(Pageable pageable) {
        return ResponseEntity.ok(expertService.getExpertsHaveCourses(pageable));
    }

    @ApiMessage("Lấy chi tiết chuyên gia thành công")
    @GetMapping("{userId}")
    public ResponseEntity<ExpertDetailsResponse> getExpertById(@PathVariable Long userId) {
        return ResponseEntity.ok(expertService.getExpertById(userId));
    }

    @ApiMessage("Theo dõi chuyên gia thành công!")
    @PostMapping("/follow/{expertId}")
    public ResponseEntity<ExpertDetailsResponse> followExpert(@PathVariable Long expertId) {
        return ResponseEntity.ok(this.expertService.followExpert(expertId));
    }

    @ApiMessage("Lấy khóa học của chuyên gia thành công")
    @GetMapping("/courses/all")
    public ResponseEntity<List<CourseDetailsResponse>> getAllCoursesByExpert() {
        return ResponseEntity.ok(expertService.getAllCoursesByExpert());
    }

    @ApiMessage("Lấy tất cả chuyên gia thành công!")
    @GetMapping
    public ResponseEntity<PageDetailsResponse<List<ExpertDetailsResponse>>> getAllExperts(
            Pageable pageable,
            @Filter Specification<ExpertEntity> specification
    ) {
        return ResponseEntity.ok(expertService.getAllExperts(pageable, specification));
    }
}
