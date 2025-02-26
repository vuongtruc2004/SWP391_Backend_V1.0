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

    Long duration;

    @Column(name = "lesson_type")
    @Enumerated(EnumType.STRING)
    LessonTypeEnum lessonType;

    @Column(name = "video_url")
    String videoUrl;

    @Column(name = "document_content", columnDefinition = "LONGTEXT")
    String documentContent;

    @ManyToOne
    @JoinColumn(name = "chapter_id")
    ChapterEntity chapter;


}
