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

    @ManyToOne
    @JoinColumn(name = "user_id")
    UserEntity user;

    @Column(name = "course_id")
    Long courseId;

    @Column(name = "chapter_id")
    Long chapterId;

    @Column(name = "lesson_id")
    Long lessonId;

    @Column(name = "quiz_id")
    Long quizId;
}
