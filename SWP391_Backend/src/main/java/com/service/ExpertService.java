package com.service;

import com.dto.response.ExpertResponse;
import com.dto.response.PageDetailsResponse;
import com.dto.response.details.ExpertDetailsResponse;
import com.entity.ExpertEntity;
import com.exception.custom.NotFoundException;
import com.helper.ExpertServiceHelper;
import com.repository.ExpertRepository;
import com.util.BuildResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpertService {
    private final ExpertRepository expertRepository;
    private final ModelMapper modelMapper;
    private final ExpertServiceHelper expertServiceHelper;
    public PageDetailsResponse<List<ExpertResponse>> getExperts(Pageable pageable) {
        Page<ExpertEntity> page = expertRepository.findAll(pageable);
        List<ExpertResponse> expertResponses = page.getContent()
                .stream().map(expertEntity -> {
                    ExpertResponse expertResponse = modelMapper.map(expertEntity, ExpertResponse.class);
                    expertResponse.setTotalCourses(expertEntity.getCourses().size());
                    return expertResponse;
                })
                .toList();

        return BuildResponse.buildPageDetailsResponse(
                page.getNumber() + 1,
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements(),
                expertResponses
        );
    }
    public ExpertDetailsResponse getExpertById(Long userId) {
        ExpertEntity expertEntity = expertRepository.findByUser_UserId(userId).
                orElseThrow(() -> new NotFoundException("Expert không tìm thấy"));
        return expertServiceHelper.convertToExpertDetailsResponse(expertEntity);

    }
}
