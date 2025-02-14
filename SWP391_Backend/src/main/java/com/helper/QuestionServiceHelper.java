package com.helper;

import com.dto.response.CourseResponse;
import com.dto.response.QuestionResponse;
import com.entity.AnswerEntity;
import com.entity.CourseEntity;
import com.entity.QuestionEntity;
import com.entity.QuizEntity;
import com.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class QuestionServiceHelper {
    private final ModelMapper modelMapper;
    private final QuizRepository quizRepository;
    public List<QuestionResponse> convertToListResponse(Page<QuestionEntity> page) {
        return page.getContent()
                .stream().map(questionEntity -> {
                    List<QuizEntity> quizEntities=questionEntity.getQuizzes().stream()
                            .collect(Collectors.toList());
                    List<AnswerEntity> answers = questionEntity.getAnswers().stream()
                            .collect(Collectors.toList());


                    QuestionResponse questionResponse = new QuestionResponse();
                    questionResponse.setQuestionId(questionEntity.getQuestionId());
                    questionResponse.setTitle(questionEntity.getTitle());
                    questionResponse.setAnswers(answers);
                    List<String> quizzContent=new ArrayList<>();
                    for(QuizEntity quizEntity : quizEntities){
                        quizzContent.add(quizEntity.getTitle());
                    }
                    questionResponse.setQuizzes(quizzContent);
                    return questionResponse;
                })
                .toList();
    }
}
