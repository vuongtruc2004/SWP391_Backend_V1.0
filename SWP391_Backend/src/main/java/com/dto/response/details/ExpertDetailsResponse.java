package com.dto.response.details;

import com.dto.response.ExpertResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExpertDetailsResponse extends ExpertResponse {

    String job;

    String achievement;

    String description;

    Integer yearOfExperience;

    Integer totalCourses;

    Integer totalStudents;
}
