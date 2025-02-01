package com.service.auth;

import com.dto.request.CredentialsLoginRequest;
import com.dto.request.SocialsLoginRequest;
import com.dto.response.LoginResponse;
import com.dto.response.UserResponse;
import com.entity.RoleEntity;
import com.entity.UserEntity;
import com.exception.custom.InvalidTokenException;
import com.exception.custom.RoleException;
import com.exception.custom.UserException;
import com.repository.RoleRepository;
import com.repository.UserRepository;
import com.util.BuildResponse;
import com.util.SecurityUtil;
import com.util.enums.RoleNameEnum;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class AuthService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final JwtService jwtService;
    private final SecurityUtil securityUtil;
    private final RoleRepository roleRepository;

    public AuthService(AuthenticationManagerBuilder authenticationManagerBuilder, UserRepository userRepository, ModelMapper modelMapper, JwtService jwtService, SecurityUtil securityUtil, RoleRepository roleRepository) {
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.jwtService = jwtService;
        this.securityUtil = securityUtil;
        this.roleRepository = roleRepository;
    }

    public LoginResponse credentialsLogin(CredentialsLoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        String username = authentication.getName();
        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserException("Username not found!"));
        if (Boolean.FALSE.equals(userEntity.getActive())) {
            throw new UserException("User is not active!");
        }

        UserResponse userResponse = modelMapper.map(userEntity, UserResponse.class);
        userResponse.setRoleName(userEntity.getRole().getRoleName());

        String accessToken = jwtService.createJWTToken(username, securityUtil.accessTokenExpiration);
        String refreshToken = jwtService.createJWTToken(username, securityUtil.refreshTokenExpiration);

        userEntity.setRefreshToken(refreshToken);
        UserEntity newUser = userRepository.save(userEntity);

        Instant now = Instant.now();
        Instant expireAt = now.plusSeconds(securityUtil.accessTokenExpiration);
        return BuildResponse.buildLoginResponse(modelMapper.map(newUser, UserResponse.class), accessToken, expireAt, refreshToken);
    }

    public LoginResponse socialsLogin(SocialsLoginRequest socialsLoginRequest) {
        Optional<UserEntity> currentUser = userRepository.findByUsernameAndAccountType(socialsLoginRequest.getUsername(), socialsLoginRequest.getAccountType());
        Instant now = Instant.now();
        Instant expireAt = now.plusSeconds(securityUtil.accessTokenExpiration);

        if (currentUser.isPresent()) {
            UserResponse userResponse = modelMapper.map(currentUser.get(), UserResponse.class);
            String accessToken = jwtService.createJWTToken(userResponse.getUsername(), securityUtil.accessTokenExpiration);
            String refreshToken = currentUser.get().getRefreshToken();
            return BuildResponse.buildLoginResponse(userResponse, accessToken, expireAt, refreshToken);
        }

        UserEntity userEntity = modelMapper.map(socialsLoginRequest, UserEntity.class);

        String refreshToken = jwtService.createJWTToken(userEntity.getUsername(), securityUtil.refreshTokenExpiration);
        RoleEntity roleEntity = roleRepository.findByRoleName(RoleNameEnum.USER).orElseThrow(() -> new RoleException("Role not found!"));
        userEntity.setRefreshToken(refreshToken);
        userEntity.setRole(roleEntity);

        UserEntity newUser = userRepository.save(userEntity);
        UserResponse userResponse = modelMapper.map(newUser, UserResponse.class);
        String accessToken = jwtService.createJWTToken(userResponse.getUsername(), securityUtil.accessTokenExpiration);
        return BuildResponse.buildLoginResponse(userResponse, accessToken, expireAt, refreshToken);
    }

    public void logout(String refreshToken) {
        NimbusJwtDecoder jwtDecoder = NimbusJwtDecoder
                .withSecretKey(securityUtil.getSecretKey())
                .macAlgorithm(securityUtil.JWT_ALGORITHMS)
                .build();

        try {
            Jwt jwt = jwtDecoder.decode(refreshToken);
            String username = jwt.getSubject();
            UserEntity userEntity = userRepository.findByUsername(username).orElseThrow(() -> new UserException("User not found!"));
            userEntity.setRefreshToken(null);
            userRepository.save(userEntity);
        } catch (Exception e) {
            throw new InvalidTokenException("Refresh token is invalid!");
        }
    }
}
