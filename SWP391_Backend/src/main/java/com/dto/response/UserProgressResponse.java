package com.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserProgressResponse {

    Long progressId;

    Long userId;

    Long courseId;

    Long chapterId;

    Long lessonId;

    Long quizId;
}
