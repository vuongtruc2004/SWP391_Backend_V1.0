package com.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonManagedReference
    UserEntity user;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    @JsonManagedReference
    QuizEntity quiz;

    @OneToMany(mappedBy = "quizAttempt")
    @JsonManagedReference
    Set<UserAnswerEntity> userAnswers;

    @Column(name = "attempt_number")
    Integer attemptNumber;

    Double score;

    @Column(name = "start_time")
    Instant startTime;

    @Column(name = "end_time")
    Instant endTime;
}
