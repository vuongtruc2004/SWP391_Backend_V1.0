package com.service;

import com.dto.request.SubjectRequest;
import com.dto.response.ApiResponse;
import com.dto.response.PageDetailsResponse;
import com.dto.response.SubjectResponse;
import com.entity.CourseEntity;
import com.entity.SubjectEntity;
import com.exception.custom.NotFoundException;
import com.exception.custom.StorageException;
import com.exception.custom.SubjectException;
import com.repository.SubjectRepository;
import com.util.BuildResponse;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;
    private final ModelMapper modelMapper;
    private final FileService fileService;


    public SubjectService(SubjectRepository subjectRepository, ModelMapper modelMapper, FileService fileService) {
        this.subjectRepository = subjectRepository;
        this.modelMapper = modelMapper;
        this.fileService = fileService;
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
                    subjectResponse.setListCourses(subjectEntity.getCourses().stream().map(CourseEntity::getCourseName).collect(Collectors.toSet()));
                    subjectResponse.setThumbnail(subjectEntity.getThumbnail());
                    return subjectResponse;
                }).toList();
        return BuildResponse.buildPageDetailsResponse(
                page.getNumber() + 1,
                page.getSize(), page.getTotalPages(),
                page.getTotalElements(),
                subjectResponses
        );
    }

    public ApiResponse<String> deleteSubject(Long subjectId) {
        if (subjectRepository.existsById(subjectId)) {
            SubjectEntity subjectEntity = subjectRepository.findById(subjectId).get();
            if (subjectEntity.getCourses().size() == 0) {
                subjectRepository.deleteById(subjectId);
            } else {
                return BuildResponse.buildApiResponse(
                        HttpStatus.BAD_REQUEST.value(),
                        "Thất bại",
                        "Không thể lĩnh vực học này do đang có khóa học!",
                        null
                );
            }
        } else {
            throw new NotFoundException("Không tìm thấy Id môn học!");
        }
        return BuildResponse.buildApiResponse(
                HttpStatus.OK.value(),
                "Thành công!",
                "Bạn đã lĩnh vực học có Id là " + subjectId + " thành công!",
                null
        );
    }

    public SubjectResponse updateSubject(SubjectRequest subjectRequest) {
        if (subjectRequest.getSubjectId() == null) {
            throw new SubjectException("ID của công nghệ không được null!");
        }
        if (subjectRepository.existsBySubjectNameAndSubjectIdNot(subjectRequest.getSubjectName(), subjectRequest.getSubjectId())) {
            throw new SubjectException("Tên công nghệ đã tồn tại!");
        }
        SubjectEntity subjectEntity = subjectRepository.findById(subjectRequest.getSubjectId())
                .orElseThrow(() -> new NotFoundException("Công nghệ không tồn tại!"));
        subjectEntity.setSubjectName(subjectRequest.getSubjectName());
        subjectEntity.setThumbnail(subjectRequest.getThumbnail());
        subjectEntity.setDescription(subjectRequest.getDescription());
        subjectEntity.setUpdatedAt(Instant.now());
        return modelMapper.map(subjectRepository.save(subjectEntity), SubjectResponse.class);
    }

    public ApiResponse<String> updateThumbnail(MultipartFile file, String folder) throws URISyntaxException, IOException {
        if (file == null || file.isEmpty()) {
            throw new StorageException("File bị rỗng");
        }
        String fileName = file.getOriginalFilename();
        List<String> allowedExtensions = Arrays.asList("jpg", "jpeg", "png");
        boolean isValid = allowedExtensions.stream().anyMatch(item -> {
            assert fileName != null;
            return fileName.toLowerCase().endsWith(item);
        });
        if (!isValid) {
            throw new StorageException("Bạn phải truyền file có định dạng:  " + allowedExtensions.toString());
        }
        return fileService.uploadFile(file, folder);
    }

    public ApiResponse<SubjectResponse> createSubject(SubjectRequest request) {
        if (request.getSubjectId() != null) {
            throw new SubjectException("Không được để id");
        }
        if (subjectRepository.existsBySubjectName(request.getSubjectName())) {
            throw new SubjectException("Tên công nghệ đã tồn tại!");
        }
        SubjectEntity subjectEntity = modelMapper.map(request, SubjectEntity.class);
        subjectRepository.save(subjectEntity);
        return BuildResponse.buildApiResponse(
                HttpStatus.CREATED.value(),
                "Tạo công nghệ thành công",
                null,
                modelMapper.map(subjectEntity, SubjectResponse.class)
        );
    }

    public List<SubjectResponse> getAllSubjectInPagination() {
        List<SubjectEntity> subjectEntities = this.subjectRepository.findAll();
        return subjectEntities.stream().map(subjectEntity -> {
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
    }
}
