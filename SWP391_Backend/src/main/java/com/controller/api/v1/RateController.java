package com.controller.api.v1;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
            @RequestParam(name = "courseId") Long courseId,
            Pageable pageable,
            @Filter Specification<RateEntity> specification
    ) {
        return ResponseEntity.ok(rateService.getRateWithFilters(courseId, pageable, specification));
    }

    @ApiMessage("Lấy số khóa học của 1 mức đánh giá thành công!")
    @GetMapping("/levels")
    public ResponseEntity<Map<Integer, Integer>> getNumberOfCoursesOfEachRate(@RequestParam(name = "courseId") Long courseId) {
        return ResponseEntity.ok(rateService.getNumberOfCoursesOfEachRate(courseId));
    }
}
