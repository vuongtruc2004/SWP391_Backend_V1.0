package com.dto.response;

import com.entity.RoleEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.util.enums.AccountTypeEnum;
import com.util.enums.GenderEnum;
import com.util.enums.JobEnum;
import jakarta.persistence.*;
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

    String username;

    String password;

    String avatar;

    String fullname;

    String email;

    @Enumerated(EnumType.STRING)
    AccountTypeEnum accountType;

    @Enumerated(EnumType.STRING)
    JobEnum job;

    @Enumerated(EnumType.STRING)
    GenderEnum gender;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+7")
    Instant dob;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+7")
    Instant createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+7")
    Instant updatedAt;
}
