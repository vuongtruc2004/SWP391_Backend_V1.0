package com.dto.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.util.deserializer.StringToInstantDeserializer;
import com.util.enums.AccountTypeEnum;
import com.util.enums.GenderEnum;
import com.util.enums.RoleNameEnum;
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
public class UserRequest {

    Long userId;

    @Pattern(
            regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
            message = "Email không đúng định dạng!"
    )
    @NotBlank(message = "Email không được bỏ trống!")
    String email;

    @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$",
            message = "Mật khẩu phải chứa ít nhất 8 kí tự, bao gồm chữ cái và số!"
    )
    @NotBlank(message = "Mật khẩu không được để trống!")
    String password;

    String avatar;

    @Enumerated(EnumType.STRING)
    RoleNameEnum roleName;

    @Enumerated(EnumType.STRING)
    AccountTypeEnum accountType;


    @NotBlank(message = "Họ và tên không được bỏ trống!")
    String fullname;

    @Enumerated(EnumType.STRING)
    GenderEnum gender;

    @JsonDeserialize(using = StringToInstantDeserializer.class)
    @DobConstraint(min = 6)
    Instant dob;

    String job;
    String achievement;
    String description;
    Integer yearOfExperience;
}