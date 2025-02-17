package com.service;

import com.dto.request.CourseRequest;
import com.dto.request.SubjectRequest;
import com.dto.response.ApiResponse;
import com.dto.response.PageDetailsResponse;
import com.dto.response.SubjectResponse;
import com.entity.CourseEntity;
import com.entity.SubjectEntity;
import com.exception.custom.NotFoundException;
import com.repository.SubjectRepository;
import com.util.BuildResponse;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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

    public PageDetailsResponse<List<SubjectResponse>> getSubjectsWithFilter(
            Pageable pageable,
            Specification<SubjectEntity> specification
    ) {
        Page<SubjectEntity> page = subjectRepository.findAll(specification, pageable);
        List<SubjectResponse> subjectResponses = page.getContent()
                .stream().map(subjectEntity -> {
                    SubjectResponse subjectResponse = modelMapper.map(subjectEntity, SubjectResponse.class);
                    subjectResponse.setSubjectId(subjectEntity.getSubjectId());
                    subjectResponse.setSubjectName(subjectEntity.getSubjectName());
                    subjectResponse.setDescription(subjectEntity.getDescription());
                    subjectResponse.setTotalCourses(subjectEntity.getCourses().size());
                    subjectResponse.setListCourses(subjectEntity.getCourses()
                            .stream()
                            .map(CourseEntity::getCourseName)
                            .collect(Collectors.toSet()));
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

    public ApiResponse<String> deleteSubject(Long subjectId) {
        if(subjectRepository.existsById(subjectId)) {
            Optional<SubjectEntity> subjectEntity=this.subjectRepository.findById(subjectId);
            if(!subjectEntity.isEmpty()) {
                subjectRepository.deleteById(subjectId);
            } else {
                return BuildResponse.buildApiResponse(
                        HttpStatus.BAD_REQUEST.value(),
                        "Thất bại",
                        "Không thể xóa môn học này do đang có khóa học!",
                        null
                );
            }
        } else {
            throw new NotFoundException("Không tìm thấy Id môn học!");
        }
        return BuildResponse.buildApiResponse(
                HttpStatus.OK.value(),
                "Thành công!",
                "Bạn đã xóa môn học có Id là " + subjectId + " thành công!",
                null
        );
    }

    public ApiResponse<SubjectResponse> updateSubject(Long subjectId, SubjectRequest subjectRequest) {
        Optional<SubjectEntity> subjectEntity=this.subjectRepository.findById(subjectId);
        if(subjectEntity.isPresent()) {
            subjectEntity = subjectRepository.findById(subjectId);
            modelMapper.map(subjectRequest, subjectEntity);

        } else {
            throw new NotFoundException("Không tìm thấy Id môn học!");
        }
        return BuildResponse.buildApiResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Thay đổi thông tin môn học",
                null,
                modelMapper.map(subjectEntity, SubjectResponse.class)
        );
    }

}
