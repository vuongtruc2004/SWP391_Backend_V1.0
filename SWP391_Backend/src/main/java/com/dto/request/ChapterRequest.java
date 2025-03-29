package com.dto.request;

import jakarta.validation.constraints.NotBlank;
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
public class ChapterRequest {

    Long chapterId;
    
    @NotNull(message = "Tiêu đề chương học không được để trống!")
    @NotBlank(message = "Tiêu đề chương học không được để trống!")
    String title;

    @NotNull(message = "Mô tả chương học không được để trống!")
    @NotBlank(message = "Mô tả chương học không được để trống!")
    String description;

    List<LessonRequest> lessons;

    QuizInfoRequest quizInfo;

    @NotNull(message = "CourseId không được để trống!")
    Long courseId;
}