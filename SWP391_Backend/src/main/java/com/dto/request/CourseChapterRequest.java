package com.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseChapterRequest {

    @NotNull(message = "CourseID không được để trống!")
    Long courseId;

    List<ChapterRequest> chapters;
}
