package com.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubjectResponse {

    Long subjectId;

    String subjectName;

    String description;

    String thumbnail;

    Integer totalCourses;

    Set<String> listCourses;

}
