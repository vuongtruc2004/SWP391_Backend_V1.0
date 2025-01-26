package com.controller.api.v1;

import com.dto.request.CredentialsLoginRequest;
import com.dto.request.SocialsLoginRequest;
import com.dto.response.LoginResponse;
import com.service.auth.AuthService;
import com.service.auth.JwtService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;
    private final JwtService jwtService;

    public AuthController(AuthService authService, JwtService jwtService) {
        this.authService = authService;
        this.jwtService = jwtService;
    }

    @PostMapping("/login/credentials")
    public LoginResponse credentialLogin(@RequestBody CredentialsLoginRequest credentialsLoginRequest) {
        return authService.credentialsLogin(credentialsLoginRequest);
    }

    @PostMapping("/login/socials")
    public LoginResponse socialLogin(@RequestBody SocialsLoginRequest socialsLoginRequest) {
        return authService.socialsLogin(socialsLoginRequest);
    }

    @GetMapping("/refresh")
    public LoginResponse letRefreshToken(@RequestParam("refresh_token") String refreshToken) {
        return jwtService.letRefreshToken(refreshToken);
    }

    @GetMapping("/logout")
    public void logout(@RequestParam("refresh_token") String refreshToken) {
        authService.logout(refreshToken);
    }
}
