package com.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuizInfoRequest {

    @NotNull(message = "QuizID chương học không được để trống!")
    @NotBlank(message = "QuizID chương học không được để trống!")
    Long quizId;
    
    String title;

    Double duration;
}
