package com.helper;

import com.dto.response.CourseResponse;
import com.dto.response.QuestionResponse;
import com.entity.CourseEntity;
import com.entity.QuestionEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class QuestionServiceHelper {
    private final ModelMapper modelMapper;

    public List<QuestionResponse> convertToListResponse(Page<QuestionEntity> page) {
        return page.getContent()
                .stream().map(questionEntity -> {
                    QuestionResponse questionResponse = new QuestionResponse();
                    questionResponse.setQuestionId(questionEntity.getQuestionId());
                    questionResponse.setTitle(questionEntity.getTitle());
                    return questionResponse;
                })
                .toList();
    }
}
