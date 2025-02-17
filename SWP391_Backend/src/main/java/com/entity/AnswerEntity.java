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
@Table(name = "answers")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AnswerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id")
    Long answerId;

    String content;

    Boolean correct;

    @ManyToOne
    @JoinColumn(name = "question_id")
    @JsonManagedReference
    QuestionEntity question;

    public AnswerEntity(String content, Boolean correct, QuestionEntity question) {
        this.content = content;
        this.correct = correct;
        this.question = question;
    }
}
