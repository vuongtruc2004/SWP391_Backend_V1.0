package com.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProgressRequest {
    Long courseId;

    Long chapterId;
    
    Long lessonId;
}
