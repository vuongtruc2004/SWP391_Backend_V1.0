package com.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OTPRequest {

    String code;

    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*\\d).{8,}$",
            message = "Mật khẩu phải chứa ít nhất 8 kí tự, ít nhất một chữ cái in hoa và chữ số!"
    )
    @NotBlank(message = "Mật khẩu không được để trống!")
    String newPassword;
}