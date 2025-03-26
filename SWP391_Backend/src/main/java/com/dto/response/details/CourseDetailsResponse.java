package com.dto.response.details;

import com.dto.response.ChapterResponse;
import com.dto.response.CourseResponse;
import com.dto.response.SubjectResponse;
import com.util.enums.CourseStatusEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

    @Enumerated(EnumType.STRING)
    CourseStatusEnum courseStatus;

    Set<SubjectResponse> subjects;

    List<ChapterResponse> chapters;

    Double averageRating;

    Integer totalRating;
}
