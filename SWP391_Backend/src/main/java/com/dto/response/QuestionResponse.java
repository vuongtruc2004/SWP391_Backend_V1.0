package com.dto.response;


import com.entity.AnswerEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuestionResponse {
    Long questionId;
    String title;
    List<AnswerEntity> answers;
    List<String> quizzes;
    List<String> correctAnswer;
    Instant createdAt;
    Instant updatedAt;
    Instant latestUpdate;
    public Instant getLatestUpdate() {
        Instant questionUpdatedAt = Optional.ofNullable(updatedAt).orElse(Instant.MIN);
        Instant answerUpdatedAt = answers != null
                ? answers.stream()
                .map(AnswerEntity::getUpdatedAt)
                .filter(Objects::nonNull)
                .max(Instant::compareTo)
                .orElse(Instant.MIN)
                : Instant.MIN;
        return questionUpdatedAt.isAfter(answerUpdatedAt) ? questionUpdatedAt : answerUpdatedAt;
    }
}
