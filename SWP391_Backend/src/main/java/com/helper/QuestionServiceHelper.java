package com.helper;

import com.dto.response.CourseResponse;
import com.dto.response.QuestionResponse;
import com.entity.AnswerEntity;
import com.entity.CourseEntity;
import com.entity.QuestionEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class QuestionServiceHelper {
    private final ModelMapper modelMapper;

    public List<QuestionResponse> convertToListResponse(Page<QuestionEntity> page) {
        return page.getContent()
                .stream().map(questionEntity -> {
                    List<AnswerEntity> correctAnswers = questionEntity.getAnswers().stream()
                            .filter(answer -> answer.getCorrect())
                            .collect(Collectors.toList());
                    List<String> correctAnswerTitles = correctAnswers.stream()
                            .map(AnswerEntity::getContent)
                            .collect(Collectors.toList());
                    QuestionResponse questionResponse = new QuestionResponse();
                    questionResponse.setQuestionId(questionEntity.getQuestionId());
                    questionResponse.setTitle(questionEntity.getTitle());
                    questionResponse.setCorrectAnswer(correctAnswerTitles);
                    return questionResponse;
                })
                .toList();
    }
}
