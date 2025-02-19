package com.helper;

import com.dto.response.details.ExpertDetailsResponse;
import com.entity.ExpertEntity;
import com.repository.ExpertRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ExpertServiceHelper {

    private final ModelMapper modelMapper;
    private final ExpertRepository expertRepository;

    public ExpertServiceHelper(ModelMapper modelMapper, ExpertRepository expertRepository) {
        this.modelMapper = modelMapper;
        this.expertRepository = expertRepository;
    }

    public ExpertDetailsResponse convertToExpertDetailsResponse(ExpertEntity expertEntity) {
        ExpertDetailsResponse expertDetailsResponse = modelMapper.map(expertEntity, ExpertDetailsResponse.class);
        expertDetailsResponse.setTotalCourses(expertEntity.getCourses().size());
        expertDetailsResponse.setTotalStudents(expertRepository.countTotalStudents(expertEntity.getExpertId()));
        return expertDetailsResponse;
    }
}
