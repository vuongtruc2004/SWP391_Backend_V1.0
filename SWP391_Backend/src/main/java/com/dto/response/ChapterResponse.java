package com.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChapterResponse {

    Long chapterId;

    String title;

    String description;

    List<VideoResponse> videos;

    List<DocumentResponse> documents;
}
