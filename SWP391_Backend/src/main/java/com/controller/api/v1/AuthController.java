package com.controller;

import com.dto.LoginDTO;
import com.dto.CredentialsLoginDTO;
import com.entity.UserEntity;
import com.exception.UserExistsException;
import com.service.UserService;
import com.util.enums.AccountTypeEnum;
import com.util.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class AuthController {

    @Value("${jwt.refresh.token.validity.in.seconds}")
    private Long refreshTokenExpiration;
    private final SecurityUtil securityUtil;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final UserService userService;
    public AuthController(AuthenticationManagerBuilder authenticationManagerBuilder,
                          SecurityUtil securityUtil, UserService userService) {
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.securityUtil = securityUtil;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<CredentialsLoginDTO> login(@RequestBody LoginDTO loginDTO) throws UserExistsException {
        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());
        Authentication authentication=authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        CredentialsLoginDTO credentialsLoginDTO =new CredentialsLoginDTO();
        UserEntity currentUser=this.userService.getUserByUserName(loginDTO.getUsername());
        if (currentUser == null || currentUser.getAccountType() == null) {
            throw new UserExistsException("Invalid username or password");
        }
        if (!currentUser.getAccountType().name().equals("CREDENTIALS")) {
            throw new UserExistsException("Invalid username or password");
        }
        CredentialsLoginDTO.UserLogin userLogin=new CredentialsLoginDTO.UserLogin();
        credentialsLoginDTO = this.userService.responseCredentialsLogin(authentication,loginDTO);
        return ResponseEntity.ok().body(credentialsLoginDTO);

    }

}
