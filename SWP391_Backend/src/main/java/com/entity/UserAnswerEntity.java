package com.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonManagedReference
    QuizAttemptEntity quizAttempt;

    @ManyToOne
    @JoinColumn(name = "question_id")
    @JsonManagedReference
    QuestionEntity question;

    @ManyToOne
    @JoinColumn(name = "answer_id")
    @JsonManagedReference
    AnswerEntity answer;
}
