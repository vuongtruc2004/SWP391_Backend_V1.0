package com.service;

import com.entity.UserEntity;
import com.repository.UserRepository;
import com.util.SecurityUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final SecurityUtil securityUtil;
    private final ModelMapper modelMapper;

    @Value("${jwt.access.token.validity.in.seconds}")
    private Long accessTokenExpiration;

    @Value("${jwt.refresh.token.validity.in.seconds}")
    private Long refreshTokenExpiration;

    public UserService(UserRepository userRepository, SecurityUtil securityUtil, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.securityUtil = securityUtil;
        this.modelMapper = modelMapper;
    }

    public UserEntity createUser(UserEntity user) {
        return this.userRepository.save(user);
    }

    public UserEntity getUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    public UserEntity getUserByUserName(String userName) {
        return this.userRepository.findByUsername(userName).orElse(null);
    }

    public boolean checkExistsByEmailOrUsername(String email, String username) {
        return this.userRepository.existsByEmailOrUsername(email, username);
    }

    public void updateUserToken(String token, String username) {
        UserEntity user = this.userRepository.findByUsername(username).orElse(null);
        if (user != null) {
            user.setRefreshToken(token);
            this.userRepository.save(user);
        }
    }
}