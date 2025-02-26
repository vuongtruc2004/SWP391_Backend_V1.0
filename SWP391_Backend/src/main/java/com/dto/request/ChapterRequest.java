package com.dto.request;

import com.entity.CourseEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

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

}