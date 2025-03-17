package com.dto.response;

import com.entity.ChapterEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.InstantSerializer;
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

    String description;

    Boolean published;

    Boolean allowSeeAnswers;

    Integer duration;

    ChapterResponse chapter;

    @JsonSerialize(using = InstantSerializer.class)
    Instant createdAt;

    //    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonSerialize(using = InstantSerializer.class)
    Instant updatedAt;

    Set<QuestionResponse> questions;
}
