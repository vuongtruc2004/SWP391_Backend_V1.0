package com.dto.response;

import com.entity.ExpertEntity;
import com.entity.LessonEntity;
import com.entity.QuestionEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
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
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuizResponse {


    Long quizId;
    String title;
    Integer maxAttempts;
    Boolean published;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+7")
    Instant createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+7")
    Instant updatedAt;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+7")
    Instant startedAt;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+7")
    Instant endedAt;
    LessonResponse lesson;

    ExpertResponse expert;

//    @OneToMany(mappedBy = "quiz",cascade = CascadeType.ALL)
//    @JsonIgnoreProperties("quizzes")
//    Set<QuizAttemptEntity> quizAttempts;

    Set<QuestionResponse> questions;


}
