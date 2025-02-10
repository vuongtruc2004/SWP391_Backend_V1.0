package com.helper;

import com.dto.request.CredentialsLoginRequest;
import com.dto.response.LoginResponse;
import com.dto.response.UserResponse;
import com.entity.UserEntity;
import com.repository.UserRepository;
import com.service.auth.JwtService;
import com.util.BuildResponse;
import com.util.SecurityUtil;
import com.util.enums.AccountTypeEnum;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class AuthServiceHelper {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final ModelMapper modelMapper;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final SecurityUtil securityUtil;

    public AuthServiceHelper(AuthenticationManagerBuilder authenticationManagerBuilder, ModelMapper modelMapper, JwtService jwtService, UserRepository userRepository, SecurityUtil securityUtil) {
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.modelMapper = modelMapper;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.securityUtil = securityUtil;
    }

    public String authenticatedCredentialsLogin(CredentialsLoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        return authentication.getName();
    }

    public LoginResponse createLoginResponse(UserEntity userEntity, String email) {
        UserResponse userResponse = modelMapper.map(userEntity, UserResponse.class);
        userResponse.setRoleName(userEntity.getRole().getRoleName());

        String accessToken = jwtService.createJWTToken(email, AccountTypeEnum.CREDENTIALS.name(), securityUtil.accessTokenExpiration);
        String refreshToken = jwtService.createJWTToken(email, AccountTypeEnum.CREDENTIALS.name(), securityUtil.refreshTokenExpiration);

        userEntity.setRefreshToken(refreshToken);
        UserEntity newUser = userRepository.save(userEntity);
        UserResponse newUserResponse = modelMapper.map(newUser, UserResponse.class);
        newUserResponse.setRoleName(userEntity.getRole().getRoleName());

        Instant now = Instant.now();
        Instant expireAt = now.plusSeconds(securityUtil.accessTokenExpiration);
        return BuildResponse.buildLoginResponse(newUserResponse, accessToken, expireAt, refreshToken);
    }
}
