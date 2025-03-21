package com.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Set;

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

    @ManyToMany
    @JoinTable(name = "user_answers_answer",
            joinColumns = @JoinColumn(name = "user_answer_id"),
            inverseJoinColumns = @JoinColumn(name = "answer_id"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    Set<AnswerEntity> answers;
}
