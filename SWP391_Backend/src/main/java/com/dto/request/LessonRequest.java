package com.dto.request;

import com.entity.ChapterEntity;
import com.util.enums.LessonTypeEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LessonRequest {

    String title;

    @Enumerated(EnumType.STRING)
    LessonTypeEnum lessonType;

    Long duration;

    String videoUrl;

    String documentContent;
}
