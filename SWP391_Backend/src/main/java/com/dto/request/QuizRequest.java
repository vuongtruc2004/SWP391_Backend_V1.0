package com.dto.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.util.deserializer.StringToInstantDeserializer;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.List;


@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuizRequest {


    Long quizId;

    String title;

    Integer maxAttempts;

    Boolean published;



    @JsonDeserialize(using = StringToInstantDeserializer.class)
    Instant startedAt;
    @JsonDeserialize(using = StringToInstantDeserializer.class)
    Instant endedAt;

    List<String> questions;
}