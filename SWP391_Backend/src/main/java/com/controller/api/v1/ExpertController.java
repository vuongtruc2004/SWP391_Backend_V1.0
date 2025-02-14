package com.controller.api.v1;

import com.dto.response.ExpertResponse;
import com.dto.response.PageDetailsResponse;
import com.service.ExpertService;
import com.util.annotation.ApiMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/experts")
@RequiredArgsConstructor
public class ExpertController {

    private final ExpertService expertService;

    @ApiMessage("Lấy tất cả chuyên gia thành công !")
    @GetMapping
    public ResponseEntity<PageDetailsResponse<List<ExpertResponse>>> getExperts(Pageable pageable) {
        return ResponseEntity.ok(expertService.getExperts(pageable));
    }
}
