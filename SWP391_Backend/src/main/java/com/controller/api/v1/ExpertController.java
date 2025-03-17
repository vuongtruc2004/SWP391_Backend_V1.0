package com.controller.api.v1;

import com.dto.response.ExpertResponse;
import com.dto.response.PageDetailsResponse;
import com.dto.response.details.ExpertDetailsResponse;
import com.service.ExpertService;
import com.util.annotation.ApiMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<Void> followExpert(@PathVariable Long expertId) {
        this.expertService.followExpert(expertId);
        return ResponseEntity.ok().build();
    }
}
