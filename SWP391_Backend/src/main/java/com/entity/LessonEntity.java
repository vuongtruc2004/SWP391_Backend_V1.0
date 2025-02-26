package com.entity;

import com.util.enums.LessonTypeEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lessons")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LessonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lesson_id")
    Long lessonId;

    String title;

    Long duration;

    @Column(name = "created_at")
    Instant createdAt;

    @Column(name = "updated_at")
    Instant updatedAt;

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

    @PrePersist
    public void handlePrePersist() {
        this.createdAt = Instant.now();
    }

    @PreUpdate
    public void handlePreUpdate() {
        this.updatedAt = Instant.now();
    }
}
