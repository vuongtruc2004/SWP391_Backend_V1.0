package com.helper;

import com.dto.response.details.ExpertDetailsResponse;
import com.entity.CourseEntity;
import com.entity.ExpertEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ExpertServiceHelper {

    private final ModelMapper modelMapper;

    public ExpertServiceHelper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ExpertDetailsResponse convertToExpertDetailsResponse(ExpertEntity expertEntity) {
        ExpertDetailsResponse expertDetailsResponse = modelMapper.map(expertEntity, ExpertDetailsResponse.class);
        expertDetailsResponse.setTotalCourses(expertEntity.getCourses().size());
        expertDetailsResponse.setTotalStudents(getTotalStudents(expertEntity));
        return expertDetailsResponse;
    }

    private int getTotalStudents(ExpertEntity expertEntity) {
        int totalStudents = 0;
        for (CourseEntity course : expertEntity.getCourses()) {
            totalStudents += course.getUsers().size();
        }
        return totalStudents;
    }
}
