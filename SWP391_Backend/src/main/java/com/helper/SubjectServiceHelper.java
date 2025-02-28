package com.helper;

import com.dto.response.CourseResponse;
import com.dto.response.SubjectResponse;
import com.entity.CourseEntity;
import com.entity.SubjectEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SubjectServiceHelper {

    private final ModelMapper modelMapper;

    public List<SubjectResponse> convertToSubjectResponseList(Collection<SubjectEntity> collection) {
        return collection.stream().map(subjectEntity -> {
                    SubjectResponse subjectResponse = modelMapper.map(subjectEntity, SubjectResponse.class);
                    return subjectResponse;
                })
                .toList();
    }
}
