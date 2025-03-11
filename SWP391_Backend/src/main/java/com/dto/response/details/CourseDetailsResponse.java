package com.dto.response.details;

import com.dto.response.ChapterResponse;
import com.dto.response.CourseResponse;
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

    Set<SubjectResponse> subjects;

    List<ChapterResponse> chapters;

    Double averageRating;

    Integer totalRating;
}
