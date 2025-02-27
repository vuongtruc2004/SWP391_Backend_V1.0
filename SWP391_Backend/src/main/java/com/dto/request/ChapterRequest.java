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
    Long chapterId;

    String title;

    String description;

    Long courseId;

    List<LessonRequest> lessons;
}