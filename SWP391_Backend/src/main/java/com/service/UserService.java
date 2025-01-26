package com.service;

import com.dto.*;
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
    public boolean checkExistsByEmailOrUsername(String email,String username) {
        return  this.userRepository.existsByEmailOrUsername(email,username);
    }
    public void updateUserToken(String token,String username) {
        UserEntity user = this.userRepository.findByUsername(username);
        if(user!=null) {
            user.setRefreshToken(token);
            this.userRepository.save(user);
        }
    }
    public ResponseCredentialsLoginDTO responseCredentialsLogin(Authentication authentication, CredentialsLoginDTO credentialsLoginDTO) {
        Instant now = Instant.now();
        Instant validityAccess = now.plus(this.accessTokenExpiration, ChronoUnit.SECONDS);
        Instant validityRefresh = now.plus(this.refreshTokenExpiration, ChronoUnit.SECONDS);
        ResponseCredentialsLoginDTO responseCredentialsLoginDTO = new ResponseCredentialsLoginDTO();
        ResponseCredentialsLoginDTO.UserLogin userLogin=new ResponseCredentialsLoginDTO.UserLogin();
        ResponseCredentialsLoginDTO.LoginAccessToken loginAccessToken=new ResponseCredentialsLoginDTO.LoginAccessToken();
        ResponseCredentialsLoginDTO.LoginRefreshToken loginRefreshToken=new ResponseCredentialsLoginDTO.LoginRefreshToken();

        UserEntity currentUser=this.getUserByUserName(credentialsLoginDTO.getUsername());
        userLogin.setId(currentUser.getUserId());
        userLogin.setUsername(currentUser.getUsername());
        userLogin.setEmail(currentUser.getEmail());
        userLogin.setAvatar(currentUser.getAvatar());
        responseCredentialsLoginDTO.setUserLogin(userLogin);

        String accessToken=this.securityUtil.createAccessToken(authentication);
        String refreshToken = this.securityUtil.createRefreshTokenWithCredential(credentialsLoginDTO.getUsername(), responseCredentialsLoginDTO);

        loginAccessToken.setAccessToken(accessToken);
        loginAccessToken.setExpiresAt(validityAccess);

        responseCredentialsLoginDTO.setAccessToken(loginAccessToken);

        loginRefreshToken.setRefreshToken(refreshToken);
        loginRefreshToken.setExpiresAt(validityRefresh);
        responseCredentialsLoginDTO.setRefreshToken(loginRefreshToken);
        this.updateUserToken(refreshToken, credentialsLoginDTO.getUsername());
        return responseCredentialsLoginDTO;
    }
    public ResponseUserDTO responseUserDTO(UserEntity user) {
        ResponseUserDTO responseUserDTO = new ResponseUserDTO();
        responseUserDTO.setUsername(user.getUsername());
        responseUserDTO.setEmail(user.getEmail());
        responseUserDTO.setId(user.getUserId());
        responseUserDTO.setFullname(user.getFullname());
        responseUserDTO.setAvatar(user.getAvatar());
        return responseUserDTO;
    }
    public ResponseSocialsLoginDTO responseSocialsLogin(Authentication authentication, SocialsLoginDTO socialsLoginDTO) {
        Instant now = Instant.now();
        Instant validityAccess = now.plus(this.accessTokenExpiration, ChronoUnit.SECONDS);
        Instant validityRefresh = now.plus(this.refreshTokenExpiration, ChronoUnit.SECONDS);
        ResponseSocialsLoginDTO responseSocialsLoginDTO = new ResponseSocialsLoginDTO();
        ResponseSocialsLoginDTO.UserLogin userLogin=new ResponseSocialsLoginDTO.UserLogin();
        ResponseSocialsLoginDTO.LoginAccessToken loginAccessToken=new ResponseSocialsLoginDTO.LoginAccessToken();
        ResponseSocialsLoginDTO.LoginRefreshToken loginRefreshToken=new ResponseSocialsLoginDTO.LoginRefreshToken();

        UserEntity currentUser=this.getUserByEmail(socialsLoginDTO.getEmail());
        userLogin.setId(currentUser.getUserId());
        userLogin.setUsername(currentUser.getUsername());
        userLogin.setEmail(currentUser.getEmail());
        userLogin.setAvatar(currentUser.getAvatar());
        userLogin.setAccountType(currentUser.getAccountType().name());
        responseSocialsLoginDTO.setUserLogin(userLogin);

        String accessToken=this.securityUtil.createAccessToken(authentication);
        String refreshToken = this.securityUtil.createRefreshTokenWithSocials(socialsLoginDTO.getEmail(), responseSocialsLoginDTO);

        loginAccessToken.setAccessToken(accessToken);
        loginAccessToken.setExpiresAt(validityAccess);

        responseSocialsLoginDTO.setAccessToken(loginAccessToken);

        loginRefreshToken.setRefreshToken(refreshToken);
        loginRefreshToken.setExpiresAt(validityRefresh);
        responseSocialsLoginDTO.setRefreshToken(loginRefreshToken);
        this.updateUserToken(refreshToken, socialsLoginDTO.getEmail());
        return responseSocialsLoginDTO;
    }
}
