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
}
