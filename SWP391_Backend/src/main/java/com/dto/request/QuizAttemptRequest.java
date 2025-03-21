package com.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuizAttemptRequest {
    Long quizAttemptId;

    List<UserAnswerRequest> userAnswers;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class UserAnswerRequest {
        Long questionId;
        List<Long> answerIds;
    }
}

