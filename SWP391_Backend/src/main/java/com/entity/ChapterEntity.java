package com.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "chapters")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChapterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chapter_id")
    Long chapterId;

    @Column(columnDefinition = "TEXT")
    String title;

    @Column(columnDefinition = "MEDIUMTEXT")
    String description;

    @ManyToOne
    @JoinColumn(name = "course_id")
    CourseEntity course;

    @OneToMany(mappedBy = "chapter", cascade = CascadeType.ALL)
    List<LessonEntity> lessons;

    @OneToOne(mappedBy = "chapter", cascade = {CascadeType.ALL})
    QuizEntity quizz;
}
