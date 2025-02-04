package com.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.util.enums.AccountTypeEnum;
import com.util.enums.GenderEnum;
import com.util.enums.RoleNameEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {

    Long userId;

    String email;

    String avatar;

    String fullname;

    String phone;

    @Enumerated(EnumType.STRING)
    AccountTypeEnum accountType;

    @Enumerated(EnumType.STRING)
    GenderEnum gender;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+7")
    Instant dob;

    @Enumerated(EnumType.STRING)
    RoleNameEnum roleName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+7")
    Instant createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+7")
    Instant updatedAt;
}
