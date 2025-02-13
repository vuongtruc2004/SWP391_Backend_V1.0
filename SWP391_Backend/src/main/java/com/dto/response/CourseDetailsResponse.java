package com.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseDetailsResponse extends CourseResponse {

    String introduction;

    List<String> objectives;

    Boolean accepted;

    Set<SubjectResponse> subjects;

    Set<LessonResponse> lessons;

    Integer totalLikes;

    Integer totalComments;
}
