package com.service;

import com.dto.response.ExpertResponse;
import com.dto.response.PageDetailsResponse;
import com.dto.response.SubjectResponse;
import com.dto.response.UserResponse;
import com.entity.ExpertEntity;
import com.entity.SubjectEntity;
import com.repository.ExpertRepository;
import com.util.BuildResponse;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpertService {
    private final ExpertRepository expertRepository;
    private final ModelMapper modelMapper;

    public ExpertService(ExpertRepository expertRepository,
                         ModelMapper modelMapper) {
        this.expertRepository = expertRepository;
        this.modelMapper = modelMapper;
    }
    public PageDetailsResponse<List<ExpertResponse>> getAllExpert(Pageable pageable) {
        Page<ExpertEntity> page = expertRepository.findAll(pageable);
        List<ExpertResponse> expertResponses = page.getContent()
                .stream().map(expertEntity -> {
                    ExpertResponse expertResponse = modelMapper.map(expertEntity, ExpertResponse.class);
                    expertResponse.setExpertId(expertEntity.getExpertId());
                    expertResponse.setYearOfExperience(expertEntity.getYearOfExperience());
                    expertResponse.setDiploma(expertEntity.getDiploma());
                    expertResponse.setUser(modelMapper.map(expertEntity.getUser(), UserResponse.class));
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
}
