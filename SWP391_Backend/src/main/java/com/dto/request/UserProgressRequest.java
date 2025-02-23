package com.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProgressRequest {
    Long courseId;

    Long lessonId;

    Long videoId;

    Long documentId;
}
