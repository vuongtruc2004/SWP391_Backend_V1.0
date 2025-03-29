package com.dto.request;

import com.util.enums.LessonTypeEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LessonRequest {

    Long lessonId;

    @NotNull(message = "Tiêu đề bài giảng không được để trống!")
    @NotBlank(message = "Tiêu đề bài giảng không được để trống!")
    String title;
    
    String description;

    @Enumerated(EnumType.STRING)
    LessonTypeEnum lessonType;

    @NotNull(message = "Thời lượng bài giảng không được để trống!")
    Long duration;

    String videoUrl;

    String documentContent;

    @NotNull(message = "ChapterId không được để trống!")
    Long chapterId;
}
