package com.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
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

        Boolean allowSeeAnswers;

        String description;

        Integer duration;

        Integer totalQuestions;

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+7")
        Instant updatedAt;

        Long chapterId;
    }
}
