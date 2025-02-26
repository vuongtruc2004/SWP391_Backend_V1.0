package com.dto.request;

import com.entity.CourseEntity;
import com.entity.LessonEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

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

    List<LessonRequest> lessons;

}