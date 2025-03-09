package com.entity;

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

    Boolean published;

    @Column(name = "allow_see_answers")
    Boolean allowSeeAnswers;

    Integer duration;

    @Column(name = "created_at")
    Instant createdAt;

    @Column(name = "updated_at")
    Instant updatedAt;

    @OneToOne
    @JoinColumn(name = "chapter_id")
    ChapterEntity chapter;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
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
        this.updatedAt = Instant.now();
    }

    @PreUpdate
    public void handlePreUpdate() {
        this.updatedAt = Instant.now();
    }
}
