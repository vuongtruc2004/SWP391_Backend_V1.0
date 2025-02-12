package com.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @JsonIgnore
    CourseEntity course;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "lesson_video",
            joinColumns = @JoinColumn(name = "lesson_id"),
            inverseJoinColumns = @JoinColumn(name = "video_id"))
    @JsonIgnoreProperties("lessons")
    Set<VideoEntity> videos;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "lesson_document",
            joinColumns = @JoinColumn(name = "lesson_id"),
            inverseJoinColumns = @JoinColumn(name = "document_id"))
    @JsonIgnoreProperties("lessons")
    Set<DocumentEntity> documents;

    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL)
    Set<QuizEntity> quizzes;
}
