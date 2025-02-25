package com.dto.request;

import com.entity.CourseEntity;
import com.entity.DocumentEntity;
import com.entity.QuizEntity;
import com.entity.VideoEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChapterRequest {
    Long lessonId;

    String title;

    String description;

    CourseEntity course;

    List<VideoEntity> videos;

    List<DocumentEntity> documents;

    Set<QuizEntity> quizzes;
}