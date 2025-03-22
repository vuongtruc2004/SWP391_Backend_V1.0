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
import com.helper.AuthServiceHelper;
import com.repository.RoleRepository;
import com.repository.UserRepository;
import com.util.BuildResponse;
import com.util.SecurityUtil;
import com.util.enums.AccountTypeEnum;
import com.util.enums.RoleNameEnum;
import org.modelmapper.ModelMapper;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final JwtService jwtService;
    private final SecurityUtil securityUtil;
    private final RoleRepository roleRepository;
    private final AuthServiceHelper authServiceHelper;

    public AuthService(UserRepository userRepository, ModelMapper modelMapper, JwtService jwtService, SecurityUtil securityUtil, RoleRepository roleRepository, AuthServiceHelper authServiceHelper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.jwtService = jwtService;
        this.securityUtil = securityUtil;
        this.roleRepository = roleRepository;
        this.authServiceHelper = authServiceHelper;
    }

    public LoginResponse credentialsLogin(CredentialsLoginRequest loginRequest) {
        String email = authServiceHelper.authenticatedCredentialsLogin(loginRequest);
        UserEntity userEntity = userRepository.findByEmailAndAccountType(email, AccountTypeEnum.CREDENTIALS)
                .orElseThrow(() -> new UserException("Tài khoản không tồn tại!"));
        if (Boolean.FALSE.equals(userEntity.getActive())) {
            throw new UserException("Tài khoản không tồn tại!");
        }
        if (!userEntity.getRole().getRoleName().equals(RoleNameEnum.USER)) {
            throw new UserException("Tài khoản của bạn không có quyền truy cập hệ thống!");
        }
        return authServiceHelper.createLoginResponse(userEntity, email);
    }

    public LoginResponse credentialsLoginAdmin(CredentialsLoginRequest loginRequest) {
        String email = authServiceHelper.authenticatedCredentialsLogin(loginRequest);
        UserEntity userEntity = userRepository.findByEmailAndAccountType(email, AccountTypeEnum.CREDENTIALS)
                .orElseThrow(() -> new UserException("Tài khoản không tồn tại!"));
        if (Boolean.FALSE.equals(userEntity.getActive())) {
            throw new UserException("Tài khoản không tồn tại!");
        }
        if (userEntity.getRole().getRoleName().equals(RoleNameEnum.USER)) {
            throw new UserException("Tài khoản của bạn không có quyền truy cập hệ thống!");
        }
        return authServiceHelper.createLoginResponse(userEntity, email);
    }

    public LoginResponse socialsLogin(SocialsLoginRequest socialsLoginRequest) {
        Optional<UserEntity> currentUser = userRepository.findByEmailAndAccountType(socialsLoginRequest.getEmail(), socialsLoginRequest.getAccountType());
        Instant now = Instant.now();
        Instant expireAt = now.plusSeconds(securityUtil.accessTokenExpiration);

        if (currentUser.isPresent()) {
            UserEntity currentUserEntity = currentUser.get();
            if (Boolean.TRUE.equals(currentUserEntity.getLocked())) {
                throw new UserException("Sai tên tài khoản hoặc mật khẩu!");
            }
            String accessToken = jwtService.createJWTToken(currentUserEntity.getEmail(), socialsLoginRequest.getAccountType().name(), securityUtil.accessTokenExpiration);
            String newRefreshToken = jwtService.createJWTToken(currentUserEntity.getEmail(), socialsLoginRequest.getAccountType().name(), securityUtil.refreshTokenExpiration);
            currentUserEntity.setRefreshToken(newRefreshToken);
            userRepository.save(currentUserEntity);
            return BuildResponse.buildLoginResponse(
                    modelMapper.map(currentUserEntity, UserResponse.class),
                    accessToken,
                    expireAt,
                    newRefreshToken
            );
        }

        UserEntity userEntity = modelMapper.map(socialsLoginRequest, UserEntity.class);

        String refreshToken = jwtService.createJWTToken(userEntity.getEmail(), socialsLoginRequest.getAccountType().name(), securityUtil.refreshTokenExpiration);
        RoleEntity roleEntity = roleRepository.findByRoleName(RoleNameEnum.USER).orElseThrow(() -> new RoleException("Role not found!"));
        userEntity.setRefreshToken(refreshToken);
        userEntity.setRole(roleEntity);

        UserEntity newUser = userRepository.save(userEntity);
        UserResponse userResponse = modelMapper.map(newUser, UserResponse.class);
        String accessToken = jwtService.createJWTToken(userResponse.getEmail(), socialsLoginRequest.getAccountType().name(), securityUtil.accessTokenExpiration);
        return BuildResponse.buildLoginResponse(userResponse, accessToken, expireAt, refreshToken);
    }

    public void logout(String refreshToken) {
        NimbusJwtDecoder jwtDecoder = NimbusJwtDecoder
                .withSecretKey(securityUtil.getSecretKey())
                .macAlgorithm(securityUtil.JWT_ALGORITHMS)
                .build();

        try {
            Jwt jwt = jwtDecoder.decode(refreshToken);
            String email = jwt.getSubject();
            String accountType = jwt.getClaim("accountType").toString();
            UserEntity userEntity = userRepository.findByEmailAndAccountType(email, AccountTypeEnum.valueOf(accountType))
                    .orElseThrow(() -> new UserException("User not found!"));
            userEntity.setRefreshToken(null);
            userRepository.save(userEntity);
        } catch (Exception e) {
            throw new InvalidTokenException("Refresh token is invalid!");
        }
    }
}
