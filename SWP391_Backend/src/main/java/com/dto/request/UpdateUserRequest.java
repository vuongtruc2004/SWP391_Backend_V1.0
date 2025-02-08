package com.dto.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.util.deserializer.StringToInstantDeserializer;
import com.util.enums.GenderEnum;
import com.util.validation.constraint.DobConstraint;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateUserRequest {

    Long userId;

    @Pattern(
            regexp = "^[a-zA-ZÀ-ỹ\\s]+$",
            message = "Họ và tên chỉ được chứa chữ cái!"
    )
    @NotBlank(message = "Họ và tên không được bỏ trống!")
    String fullname;

    @Enumerated(EnumType.STRING)
    GenderEnum gender;

    @JsonDeserialize(using = StringToInstantDeserializer.class)
    @DobConstraint(min = 7)
    Instant dob;
}
