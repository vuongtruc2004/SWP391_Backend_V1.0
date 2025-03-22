package com.dto.request;

import com.util.enums.AccountTypeEnum;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SocialsLoginRequest {

    String email;

    String fullname;

    String avatar;

    AccountTypeEnum accountType;
}
