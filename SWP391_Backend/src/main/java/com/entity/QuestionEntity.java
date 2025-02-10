package com.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "questions")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuestionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    Long questionId;

    String title;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    Set<AnswerEntity> answers;

    @ManyToMany(mappedBy = "questions",cascade = CascadeType.ALL)
    List<QuizEntity> quizzes;

    public QuestionEntity(String title) {
        this.title = title;
    }
}