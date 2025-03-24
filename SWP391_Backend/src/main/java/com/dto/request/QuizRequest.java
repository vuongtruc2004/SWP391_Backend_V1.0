package com.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuizRequest {

    Long quizId;

    String title;

    Boolean published;

    Boolean allowSeeAnswers;

    String description;

    Integer duration;

    Long chapterId;

    @NotNull(message = "BankQuestionId không được null")
    List<Long> bankQuestionIds; // question in banks
    
    @NotNull(message = "newQuestion không được null")
    List<QuestionRequest> newQuestions; // new questions
}