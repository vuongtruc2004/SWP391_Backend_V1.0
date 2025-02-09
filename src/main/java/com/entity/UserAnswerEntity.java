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
@Table(name = "user_answers")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserAnswerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_answer_id")
    Long userAnswerId;

    @ManyToOne
    @JoinColumn(name = "quiz_attempt_id")
    QuizAttemptEntity quizAttempt;

    @ManyToOne
    @JoinColumn(name = "question_id")
    QuestionEntity question;

    @ManyToOne
    @JoinColumn(name = "answer_id")
    AnswerEntity answer;
}
