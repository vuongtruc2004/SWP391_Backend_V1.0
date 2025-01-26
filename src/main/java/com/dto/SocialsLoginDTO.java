package com.dto;

import com.util.enums.AccountTypeEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SocialsLoginDTO {
    private String email;
    private AccountTypeEnum accountType;
    private String fullName;
    private String avatar;
    private String username;
}
