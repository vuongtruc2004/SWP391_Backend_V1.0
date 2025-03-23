package com.controller.api.v1;

import com.dto.request.CampaignRequest;
import com.dto.response.CampaignResponse;
import com.dto.response.MinMaxPriceResponse;
import com.dto.response.PageDetailsResponse;
import com.entity.CampaignEntity;
import com.service.CampaignService;
import com.turkraft.springfilter.boot.Filter;
import com.util.annotation.ApiMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/campaigns")
@RequiredArgsConstructor
public class CampaignController {

    private final CampaignService campaignService;

    @ApiMessage("Lấy tất cả chiến dịch thành công!")
    @GetMapping
    public ResponseEntity<PageDetailsResponse<List<CampaignResponse>>> getAllCampaignsWithFilter(
            Pageable pageable,
            @Filter Specification<CampaignEntity> specification
    ) {
        return ResponseEntity.ok(campaignService.getAllCampaignsWithFilter(pageable, specification));
    }

    @ApiMessage("Tạo chiến dịch thành công!")
    @PostMapping
    public ResponseEntity<CampaignResponse> createCampaign(@RequestBody CampaignRequest request) {
        return ResponseEntity.ok(campaignService.createCampaign(request));
    }

    @ApiMessage("Xóa chiến dịch thành công!")
    @DeleteMapping("/{campaignId}")
    public ResponseEntity<Void> deleteCampaign(@PathVariable Long campaignId) {
        campaignService.deleteCampaign(campaignId);
        return ResponseEntity.ok().build();
    }

    @ApiMessage("Cập nhật chiến dịch thành công!")
    @PatchMapping
    public ResponseEntity<CampaignResponse> updateCampaign(@RequestBody CampaignRequest request) {
        return ResponseEntity.ok(campaignService.updateCampaign(request));
    }

    @ApiMessage("Lấy tất cả chiến dịch không phân trang thành công!")
    @GetMapping("/all")
    public ResponseEntity<List<CampaignResponse>> getAllCampaigns() {
        return ResponseEntity.ok(campaignService.getAllCampaigns());
    }

    @ApiMessage("Lấy khoảng giá chiến dịch thành công!")
    @GetMapping("/price-range")
    public ResponseEntity<MinMaxPriceResponse> getRangePrice() {
        return ResponseEntity.ok(campaignService.getMaxMinPriceOfCourses());
    }

}