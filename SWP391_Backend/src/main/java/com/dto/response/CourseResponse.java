package com.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.util.enums.CourseStatusEnum;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseResponse {

    Long courseId;

    String courseName;

    String description;

    String thumbnail;

    List<String> objectives;

    Double price;

    ExpertResponse expert;

    Integer totalPurchased;

    Integer totalLessons;

    Integer totalQuizzes;

    CourseStatusEnum courseStatus;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+7")
    Instant createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+7")
    Instant updatedAt;
}
