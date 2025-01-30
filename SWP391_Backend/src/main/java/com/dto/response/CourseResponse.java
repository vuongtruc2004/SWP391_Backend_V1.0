package com.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.List;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseResponse {

    Long courseId;

    String courseName;

    String description;

    List<String> objectives;

    String thumbnail;

    Double price;

    Boolean accepted;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+7")
    Instant createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+7")
    Instant updatedAt;

    Set<SubjectResponse> subjects;

    Set<LessonResponse> lessons;

    ExpertResponse expert;

    Integer totalPurchased;

    Integer totalLikes;

    Integer totalComments;

}
