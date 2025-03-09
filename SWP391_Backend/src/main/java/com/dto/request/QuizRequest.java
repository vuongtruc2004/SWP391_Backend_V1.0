package com.dto.request;

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

    Integer duration;

    Long chapterId;

    List<Long> bankQuestionIds; // question in banks

    List<QuestionRequest> newQuestions; // new questions
}