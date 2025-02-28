package com.dto.response;


import com.entity.AnswerEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
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
    List<AnswerEntity> answers;
    List<String> quizzes;
    List<String> correctAnswer;
    Instant createdAt;
    Instant updatedAt;
    Instant latestUpdate;
}
