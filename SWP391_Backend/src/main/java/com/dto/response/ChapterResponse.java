package com.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChapterResponse {

    Long chapterId;

    String title;

    String description;

    List<LessonResponse> lessons;

    QuizInfoResponse quizInfo;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @Builder
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class QuizInfoResponse {
        Long quizId;

        String title;

        Boolean published;
        
        Integer duration;

        Integer totalQuestions;
    }
}
