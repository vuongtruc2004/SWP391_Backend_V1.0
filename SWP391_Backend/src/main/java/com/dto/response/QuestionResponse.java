package com.dto.response;


import com.entity.AnswerEntity;
import com.entity.QuestionEntity;
import com.entity.QuizEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuestionResponse {
    Long questionId;
    String title;
    List<String> correctAnswer;



}
