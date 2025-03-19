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
    Long userId;
    Long quizId;
    Integer attemptNumber;
    Double score;
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

