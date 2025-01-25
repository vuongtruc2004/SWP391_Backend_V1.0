package com.dto.response;

import com.entity.RoleEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.util.enums.GenderEnum;
import com.util.enums.JobTypeEnum;
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
    String phone;
    String password;
    String fullname;
    String email;
    GenderEnum gender;
    JobTypeEnum jobType;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+7")
    Instant dob;
    Boolean active;
    RoleEntity role;
}
