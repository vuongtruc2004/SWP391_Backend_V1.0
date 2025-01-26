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
@Table(name = "lessons")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LessonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lesson_id")
    Long lessonId;

    String title;

    @Column(columnDefinition = "MEDIUMTEXT")
    String description;

    @ManyToOne
    @JoinColumn(name = "course_id")
    CourseEntity course;

    @OneToMany(mappedBy = "lesson")
    List<VideoEntity> videos;

    @OneToMany(mappedBy = "lesson")
    List<DocumentEntity> documents;

    @OneToOne
    @JoinColumn(name = "test_id")
    TestEntity test;
}
