package com.entity;

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
@Table(name = "answers")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AnswerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id")
    Long answerId;

    @Column(columnDefinition = "MEDIUMTEXT")
    String content;

    Boolean correct;

    @ManyToOne
    @JoinColumn(name = "question_id")
    QuestionEntity question;

    @Column(name = "created_at")
    Instant createdAt;

    @Column(name = "updated_at")
    Instant updatedAt;

    @PrePersist
    public void handlePrePersist() {
        this.createdAt = Instant.now();
    }

    @PreUpdate
    public void handlePreUpdate() {
        this.updatedAt = Instant.now();
    }
}
