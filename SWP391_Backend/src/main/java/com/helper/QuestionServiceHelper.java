package com.helper;

import com.dto.response.QuestionResponse;
import com.entity.AnswerEntity;
import com.entity.QuestionEntity;
import com.entity.QuizEntity;
import com.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class QuestionServiceHelper {

    public List<QuestionResponse> convertToListResponse(Page<QuestionEntity> page) {
        return page.getContent()
                .stream()
                .map(questionEntity -> {
                    List<QuizEntity> quizEntities = new ArrayList<>(questionEntity.getQuizzes());
                    List<AnswerEntity> answers = new ArrayList<>(questionEntity.getAnswers());
                    QuestionResponse questionResponse = new QuestionResponse();
                    questionResponse.setQuestionId(questionEntity.getQuestionId());
                    questionResponse.setTitle(questionEntity.getTitle());
                    questionResponse.setAnswers(answers);
                    List<String> quizzContent = quizEntities.stream()
                            .map(QuizEntity::getTitle)
                            .collect(Collectors.toList());
                    questionResponse.setQuizzes(quizzContent);
                    questionResponse.setCreatedAt(questionEntity.getCreatedAt());
                    questionResponse.setUpdatedAt(questionEntity.getUpdatedAt());
                    Instant latestUpdate = Stream.concat(
                                    Stream.of(questionEntity.getUpdatedAt()),
                                    answers.stream().map(AnswerEntity::getCreatedAt)
                            )
                            .filter(Objects::nonNull)
                            .max(Comparator.naturalOrder())
                            .orElse(null);
                    questionResponse.setLatestUpdate(latestUpdate);
                    return questionResponse;
                })
                .sorted(Comparator.comparing(
                        QuestionResponse::getLatestUpdate, Comparator.nullsLast(Comparator.reverseOrder())
                ))
                .toList();
    }




}
