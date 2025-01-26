package com.service;


import com.dto.request.SubjectRequest;
import com.dto.response.SubjectResponse;
import com.entity.SubjectEntity;
import com.exception.custom.NotFoundException;
import com.repository.SubjectRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SubjectService {

    SubjectRepository subjectRepository;
    ModelMapper modelMapper;

    public List<SubjectResponse> getAllSubject() {
        return subjectRepository.findAll().stream()
                .map(subjectEntity -> modelMapper.map(subjectEntity, SubjectResponse.class))
                .toList();
    }

    public SubjectResponse getSubjectByName(String name) throws NotFoundException {
        if(!subjectRepository.existsByName(name)) {
            throw new NotFoundException("Tên môn học không tồn tại!");
        }
        SubjectEntity userEntity = subjectRepository.findByName(name).orElse(null);
        return modelMapper.map(userEntity, SubjectResponse.class);
    }

    public SubjectResponse getSubjectById(Long id) throws NotFoundException {
        if(!subjectRepository.existsById(id)) {
            throw new NotFoundException("Tên môn học không tồn tại!");
        }
        SubjectEntity userEntity = subjectRepository.findBySubjectId(id).orElse(null);
        int numberOfCourses = userEntity.getCourses().size();
        SubjectResponse subjectResponse = modelMapper.map(userEntity, SubjectResponse.class);
        subjectResponse.setNumberOfCourses(numberOfCourses);
        return subjectResponse;
    }


    public SubjectResponse updateSubject(String  subjectId_raw, SubjectRequest request) throws NotFoundException {
        try {
            Long subjectId = Long.parseLong(subjectId_raw);
            if(!subjectRepository.existsById(subjectId)) {
                throw new NotFoundException("Id của môn học không tồn tại!");
            }
            SubjectEntity subjectEntity = subjectRepository.findById(subjectId).orElse(null);
            subjectEntity.setName(request.getName());
            subjectRepository.save(subjectEntity);
            return modelMapper.map(subjectEntity, SubjectResponse.class);
        } catch (NumberFormatException e) {
            throw new NotFoundException("Id của môn học phải là 1 số nguyên!");
        }
    }

    public SubjectResponse createSubject(SubjectRequest request) throws NotFoundException {
        if(subjectRepository.existsByName(request.getName())) {
            throw new NotFoundException("Tên môn học đã tồn tại!");
        }
        SubjectEntity subjectEntity = modelMapper.map(request, SubjectEntity.class);
        subjectRepository.save(subjectEntity);
        return modelMapper.map(subjectEntity, SubjectResponse.class);
    }

    public String  deleteSubject(Long subjectId) throws NotFoundException {
        SubjectEntity subjectEntity = subjectRepository.findById(subjectId).orElse(null);
        if(subjectEntity == null) {
            throw new NotFoundException("Không tìm thấy môn học!");
        }
        int num = subjectEntity.getCourses().size();
        if(num == 0) {
            subjectRepository.deleteById(subjectId);
            return "Bạn đã xóa thành công môn học có ID = " + subjectId;
        } else {
            return "Không thể xóa môn học này. Do môn học này đang có khóa học!";
        }

    }

}
