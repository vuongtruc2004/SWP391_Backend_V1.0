package com.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CredentialsLoginDTO {
    private LoginAccessToken accessToken;
    private UserLogin userLogin;
    private LoginRefreshToken refreshToken;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserLogin{
        private Long id;
        private String username;
        private String email;
        private String fullname;
        private String avatar;
    }


    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LoginAccessToken{
        private String accessToken;
        private Instant expiresAt;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LoginRefreshToken{
        private String refreshToken;
        private Instant expiresAt;
    }

}
