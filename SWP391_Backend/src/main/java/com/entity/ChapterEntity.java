package com.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lessons")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChapterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lesson_id")
    Long chapterId;

    String title;

    @Column(columnDefinition = "MEDIUMTEXT")
    String description;

    @ManyToOne
    @JoinColumn(name = "course_id")
    CourseEntity course;

    @OneToMany(mappedBy = "chapter", cascade = CascadeType.ALL)
    Set<VideoEntity> videos;

    @OneToMany(mappedBy = "chapter", cascade = CascadeType.ALL)
    Set<DocumentEntity> documents;

    @OneToMany(mappedBy = "chapter", cascade = CascadeType.ALL)
    Set<QuizEntity> quizzes;
}
