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

    public PageDetailsResponse<List<SubjectResponse>> getSubjectsSortByNumberOfCourses(Pageable pageable) {
        Page<SubjectEntity> page = subjectRepository.findAllOrderByNumberOfCourses(pageable);
        List<SubjectResponse> subjectResponseList = page.getContent()
                .stream()
                .map(subjectEntity -> {
                    SubjectResponse subjectResponse = modelMapper.map(subjectEntity, SubjectResponse.class);
                    subjectResponse.setTotalCourses(subjectEntity.getCourses().size());
                    return subjectResponse;
                })
                .toList();

        return BuildResponse.buildPageDetailsResponse(
                page.getNumber() + 1,
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements(),
                subjectResponseList
        );
    }

    public PageDetailsResponse<List<SubjectResponse>> getSubjects(Pageable pageable) {
        Page<SubjectEntity> page = subjectRepository.findAll(pageable);
        List<SubjectResponse> subjectResponses = page.getContent()
                .stream().map(subjectEntity -> {
                    SubjectResponse subjectResponse = modelMapper.map(subjectEntity, SubjectResponse.class);
                    subjectResponse.setSubjectId(subjectEntity.getSubjectId());
                    subjectResponse.setSubjectName(subjectEntity.getSubjectName());
                    subjectResponse.setDescription(subjectEntity.getDescription());
                    subjectResponse.setTotalCourses(subjectEntity.getCourses().size());
                    subjectResponse.setThumbnail(subjectEntity.getThumbnail());
                    return subjectResponse;
                })
                .toList();

        return BuildResponse.buildPageDetailsResponse(
                page.getNumber() + 1,
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements(),
                subjectResponses
        );
    }
}
