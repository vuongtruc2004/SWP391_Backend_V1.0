package com.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CredentialsLoginRequest {
    private String email;
    private String password;
}
