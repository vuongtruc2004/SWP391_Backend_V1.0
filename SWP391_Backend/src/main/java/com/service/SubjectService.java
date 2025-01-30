package com.service;

import com.dto.response.PageDetailsResponse;
import com.dto.response.SubjectResponse;
import com.entity.SubjectEntity;
import com.repository.SubjectRepository;
import com.util.BuildResponse;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;
    private final ModelMapper modelMapper;

    public SubjectService(SubjectRepository subjectRepository, ModelMapper modelMapper) {
        this.subjectRepository = subjectRepository;
        this.modelMapper = modelMapper;
    }

    public PageDetailsResponse<List<SubjectResponse>> getSubjects(Pageable pageable) {
        Page<SubjectEntity> page = subjectRepository.findAll(pageable);
        List<SubjectResponse> subjectResponseList = page.getContent()
                .stream()
                .map(subjectEntity -> modelMapper.map(subjectEntity, SubjectResponse.class))
                .toList();

        return BuildResponse.buildPageDetailsResponse(
                page.getNumber() + 1,
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements(),
                subjectResponseList
        );
    }
}
