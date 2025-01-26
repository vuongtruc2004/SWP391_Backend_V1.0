package com.dto.request;

import com.util.enums.AccountTypeEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SocialsLoginRequest {
    private String email;
    private AccountTypeEnum accountType;
    private String fullName;
    private String avatar;
    private String username;
}
