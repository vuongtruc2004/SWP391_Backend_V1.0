package com.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseStatusResponse {

    Long courseId;

    Double completionPercentage;

    Long totalCompletionVideosAndDocuments;
}
