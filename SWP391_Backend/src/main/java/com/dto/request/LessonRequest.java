package com.dto.request;

import com.util.enums.LessonTypeEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LessonRequest {
    String title;

    String description;

    @Enumerated(EnumType.STRING)
    LessonTypeEnum lessonType;

    Long duration;

    String videoUrl;

    String documentContent;
}
