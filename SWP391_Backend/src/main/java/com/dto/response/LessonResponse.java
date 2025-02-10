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
public class LessonResponse {

    Long lessonId;

    String title;

    String description;

    Set<VideoResponse> videos;

    Set<DocumentResponse> documents;
}
