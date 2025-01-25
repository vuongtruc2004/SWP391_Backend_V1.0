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
public class UserRequest {

    Long userId;

    @NotBlank(message = "Tên người dùng không được để trống!")
    String username;
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*\\d).{8,}$",
            message = "Mật khẩu phải chứa ít nhất 8 kí tự, ít nhất một chữ cái in hoa và chữ số!"
    )

    @NotBlank(message = "Mật khẩu không được để trống!")
    String password;

    @NotBlank(message = "Mật khẩu không được để trống!")
    String rePassword;

    String avatar;

    String phone;

    @Pattern(
            regexp = "^[a-zA-ZÀ-ỹ\\s]+$",
            message = "Họ và tên chỉ được chứa chữ cái!"
    )
            @NotBlank(message = "Họ và tên không được bỏ trống!")
    String fullname;


    @Pattern(
            regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
            message = "Email không đúng định dạng!"
    )
            @NotBlank(message = "Email không được bỏ trống!")
    String email;

    @Enumerated(EnumType.STRING)
    GenderEnum gender;

    @JsonDeserialize(using = StringToInstantDeserializer.class)
    @DobConstraint(min = 6)
    Instant dob;

}