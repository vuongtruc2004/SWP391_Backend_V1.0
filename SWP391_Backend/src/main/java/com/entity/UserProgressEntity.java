package com.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_progress")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserProgressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "progress_id")
    Long progressId;

    @Column(name = "user_id")
    Long userId;

    @Column(name = "course_id")
    Long courseId;

    @Column(name = "lesson_id")
    Long lessonId;

    @Column(name = "video_id")
    Long videoId;

    @Column(name = "document_id")
    Long documentId;
}
