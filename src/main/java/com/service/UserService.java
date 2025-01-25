package com.service;

import com.dto.CredentialsLoginDTO;
import com.dto.LoginDTO;
import com.entity.UserEntity;
import com.repository.custom.UserRepository;
import com.util.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final SecurityUtil securityUtil;
    @Value("${jwt.access.token.validity.in.seconds}")
    private Long accessTokenExpiration;

    @Value("${jwt.refresh.token.validity.in.seconds}")
    private Long refreshTokenExpiration;
    public UserService(UserRepository userRepository, SecurityUtil securityUtil) {
        this.userRepository = userRepository;
        this.securityUtil = securityUtil;
    }
    public UserEntity createUser(UserEntity user) {
        return this.userRepository.save(user);
    }
    public UserEntity getUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }
    public UserEntity getUserByUserName(String userName) {
        return this.userRepository.findByUsername(userName);
    }
    public void updateUserToken(String token,String username) {
        UserEntity user = this.userRepository.findByUsername(username);
        if(user!=null) {
            user.setRefreshToken(token);
            this.userRepository.save(user);
        }
    }
    public CredentialsLoginDTO responseCredentialsLogin(Authentication authentication, LoginDTO loginDTO) {
        Instant now = Instant.now();
        Instant validityAccess = now.plus(this.accessTokenExpiration, ChronoUnit.SECONDS);
        Instant validityRefresh = now.plus(this.refreshTokenExpiration, ChronoUnit.SECONDS);
        CredentialsLoginDTO credentialsLoginDTO = new CredentialsLoginDTO();
        CredentialsLoginDTO.UserLogin userLogin=new CredentialsLoginDTO.UserLogin();
        CredentialsLoginDTO.LoginAccessToken loginAccessToken=new CredentialsLoginDTO.LoginAccessToken();
        CredentialsLoginDTO.LoginRefreshToken loginRefreshToken=new CredentialsLoginDTO.LoginRefreshToken();

        UserEntity currentUser=this.getUserByUserName(loginDTO.getUsername());
        userLogin.setId(currentUser.getUserId());
        userLogin.setUsername(currentUser.getUsername());
        userLogin.setEmail(currentUser.getEmail());
        userLogin.setAvatar(currentUser.getAvatar());
        credentialsLoginDTO.setUserLogin(userLogin);

        String accessToken=this.securityUtil.createAccessToken(authentication);
        String refreshToken = this.securityUtil.createRefreshToken(loginDTO.getUsername(), credentialsLoginDTO);

        loginAccessToken.setAccessToken(accessToken);
        loginAccessToken.setExpiresAt(validityAccess);

        credentialsLoginDTO.setAccessToken(loginAccessToken);

        loginRefreshToken.setRefreshToken(refreshToken);
        loginRefreshToken.setExpiresAt(validityRefresh);
        credentialsLoginDTO.setRefreshToken(loginRefreshToken);
        this.updateUserToken(refreshToken, loginDTO.getUsername());
        return credentialsLoginDTO;

    }
}
