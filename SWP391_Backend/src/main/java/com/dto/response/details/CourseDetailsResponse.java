package com.dto.response.details;

import com.dto.response.CourseResponse;
import com.dto.response.LessonResponse;
import com.dto.response.SubjectResponse;
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

    ExpertDetailsResponse expert;

    Set<SubjectResponse> subjects;

    Set<LessonResponse> lessons;

    Double averageRating;

    Integer totalRating;
}
