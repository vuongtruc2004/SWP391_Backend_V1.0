package com.entity;

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
@Table(name = "answers")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AnswerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id")
    Long answerId;

    String content;

    @ManyToMany(mappedBy = "answers")
    Set<QuestionEntity> questions;

    @ManyToMany(mappedBy = "correctAnswers")
    Set<QuestionEntity> questionsByAnswer;
}
