package com.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "quizzes")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuizEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quiz_id")
    Long quizId;

    String title;

    @Column(name = "max_attempts")
    Integer maxAttempts;

    Boolean published;

    @Column(name = "published_at")
    Instant publishedAt;

    @Column(name = "created_at")
    Instant createdAt;

    @Column(name = "udpated_at")
    Instant updatedAt;

    @Column(name = "started_at")
    Instant startedAt;

    @Column(name = "ended_at")
    Instant endedAt;

    @ManyToOne
    @JoinColumn(name = "lesson_id")
    LessonEntity lesson;

    @ManyToOne
    @JoinColumn(name = "expert_id")
    ExpertEntity expert;

    @OneToMany(mappedBy = "quiz",cascade = CascadeType.ALL)
    @JsonIgnoreProperties("quizzes")
    Set<QuizAttemptEntity> quizAttempts;

    @ManyToMany
    @JoinTable(
            name = "quiz_question",
            joinColumns = @JoinColumn(name = "quiz_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id")
    )
    Set<QuestionEntity> questions;

    @PrePersist
    public void handlePrePersist() {
        this.createdAt = Instant.now();
    }

    @PreUpdate
    public void handlePreUpdate() {
        this.updatedAt = Instant.now();
    }
}
