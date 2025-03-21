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
@Table(name = "quiz_attempts")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuizAttemptEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quiz_attempt_id")
    Long quizAttemptId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    UserEntity user;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    QuizEntity quiz;

    @OneToMany(mappedBy = "quizAttempt", cascade = CascadeType.ALL)
    Set<UserAnswerEntity> userAnswers;

    @Column(name = "attempt_number")
    Integer attemptNumber;

    @Column(name = "number_of_corrects")
    Integer numberOfCorrects;

    @Column(name = "start_time")
    Instant startTime;

    @Column(name = "end_time")
    Instant endTime;

    @PrePersist
    public void handlePrePersist() {
        startTime = Instant.now();
        numberOfCorrects = 0;
    }
}
