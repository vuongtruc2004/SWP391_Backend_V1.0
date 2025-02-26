package com.dto.response;

import com.util.enums.LessonTypeEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LessonResponse {

    Long lessonId;

    String title;

    Long duration;

    Instant createdAt;

    Instant updatedAt;

    @Enumerated(EnumType.STRING)
    LessonTypeEnum lessonType;

    String videoUrl;

    String documentContent;
}
