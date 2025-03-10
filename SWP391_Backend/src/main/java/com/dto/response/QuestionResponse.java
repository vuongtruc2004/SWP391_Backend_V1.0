package com.dto.response;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuestionResponse {
    Long questionId;
    String title;
    Set<AnswerResponse> answers;

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class AnswerResponse {
        Long answerId;
        String content;
        Boolean correct;


    }
    //    Long questionId;
//    String title;
//    List<AnswerEntity> answers;
//    List<String> correctAnswer;
//    Instant createdAt;
//    Instant updatedAt;
//    Instant latestUpdate;
//    public Instant getLatestUpdate() {
//        Instant questionUpdatedAt = Optional.ofNullable(updatedAt).orElse(Instant.MIN);
//        Instant answerUpdatedAt = answers != null
//                ? answers.stream()
//                .map(AnswerEntity::getUpdatedAt)
//                .filter(Objects::nonNull)
//                .max(Instant::compareTo)
//                .orElse(Instant.MIN)
//                : Instant.MIN;
//        return questionUpdatedAt.isAfter(answerUpdatedAt) ? questionUpdatedAt : answerUpdatedAt;
//    }
}
