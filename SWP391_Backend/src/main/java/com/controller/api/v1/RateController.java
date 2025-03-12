package com.controller.api.v1;

import com.dto.request.RateRequest;
import com.dto.response.ApiResponse;
import com.dto.response.PageDetailsResponse;
import com.dto.response.RateResponse;
import com.entity.RateEntity;
import com.service.RateService;
import com.turkraft.springfilter.boot.Filter;
import com.util.annotation.ApiMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/rates")
@RequiredArgsConstructor
public class RateController {

    private final RateService rateService;

    @ApiMessage("Lấy các đánh giá của 1 khóa học thành công!")
    @GetMapping
    public ResponseEntity<PageDetailsResponse<List<RateResponse>>> getRateWithFilters(
            @Filter Specification<RateEntity> specification,
            Pageable pageable
    ) {
        return ResponseEntity.ok(rateService.getRateWithFilters(specification, pageable));
    }

    @ApiMessage("Lấy số khóa học của 1 mức đánh giá thành công!")
    @GetMapping("/levels")
    public ResponseEntity<Map<Integer, Integer>> getNumberOfCoursesOfEachRate(@RequestParam(name = "courseId") Long courseId) {
        return ResponseEntity.ok(rateService.getNumberOfCoursesOfEachRate(courseId));
    }


    @ApiMessage("Lấy đánh giá của bản thân về khóa học thành công!")
    @GetMapping("/my-rate/{courseId}")
    public ResponseEntity<ApiResponse<RateResponse>> getMyRate(@PathVariable Long courseId) {
        return ResponseEntity.ok(rateService.getMyCourseRating(courseId));
    }

    @ApiMessage("Đánh giá khóa học thành công!")
    @PostMapping
    public ResponseEntity<ApiResponse<RateResponse>> rateCourse(@RequestBody RateRequest rateRequest ) {
        return ResponseEntity.ok(rateService.rateCourse(rateRequest));
    }

    @ApiMessage("Xóa đánh giá khóa học thành công!")
    @DeleteMapping("/delete/{rateId}")
    public ResponseEntity<ApiResponse<String>> deleteRating(@PathVariable Long rateId) {
        return ResponseEntity.ok(rateService.deleteRating(rateId));
    }

    @ApiMessage("Chỉnh sửa đánh giá thành công!")
    @PatchMapping("/{rateId}")
    public ResponseEntity<ApiResponse<RateResponse>> updateRating(@PathVariable Long rateId, @RequestBody RateRequest rateRequest) {
        return ResponseEntity.ok(rateService.updateRating(rateId, rateRequest));
    }


}
