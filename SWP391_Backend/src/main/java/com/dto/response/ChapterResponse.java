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

    CourseResponse course;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @Builder
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class QuizInfoResponse {
        Long quizId;

        String title;

        Boolean allowSeeAnswers;

        String description;

        Integer duration;

        List<QuestionResponse> questions;

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+7")
        Instant updatedAt;

        Long chapterId;
    }
}
