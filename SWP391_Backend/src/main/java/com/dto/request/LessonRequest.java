package com.dto.request;

import com.entity.CourseEntity;
import com.entity.DocumentEntity;
import com.entity.QuizEntity;
import com.entity.VideoEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LessonRequest {
    Long lessonId;

    String title;

    CourseEntity course;

    Set<VideoEntity> videos;

    Set<DocumentEntity> documents;

    Set<QuizEntity> quizzes;
}
