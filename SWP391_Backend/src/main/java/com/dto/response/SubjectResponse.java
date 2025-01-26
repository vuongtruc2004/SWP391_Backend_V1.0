package com.dto.response;

import com.entity.CourseEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubjectResponse {
    Long subjectId;
    String name;
    String description;
    int numberOfCourses;
}
