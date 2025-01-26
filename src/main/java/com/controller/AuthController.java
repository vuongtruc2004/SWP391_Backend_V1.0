package com.controller;

import com.dto.CredentialsLoginDTO;
import com.dto.ResponseCredentialsLoginDTO;
import com.dto.ResponseSocialsLoginDTO;
import com.dto.SocialsLoginDTO;
import com.entity.UserEntity;
import com.exception.UserExistsException;
import com.service.UserDetailsCustom;
import com.service.UserService;
import com.util.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final UserDetailsCustom userDetailsCustom;
    @Value("${jwt.refresh.token.validity.in.seconds}")
    private Long refreshTokenExpiration;
    private final SecurityUtil securityUtil;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    public AuthController(AuthenticationManagerBuilder authenticationManagerBuilder,
                          SecurityUtil securityUtil, UserService userService,
                          PasswordEncoder passwordEncoder,
                          UserDetailsCustom userDetailsCustom) {
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.securityUtil = securityUtil;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsCustom = userDetailsCustom;
    }

    @PostMapping("/credentials-login")
    public ResponseEntity<ResponseCredentialsLoginDTO> credentialsLogin(@RequestBody CredentialsLoginDTO credentialsLoginDTO) throws UserExistsException {
        if(credentialsLoginDTO.getUsername()==null || credentialsLoginDTO.getPassword()==null){
            throw new UserExistsException("Username or password is empty !");
        }
        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(credentialsLoginDTO.getUsername(), credentialsLoginDTO.getPassword());
        Authentication authentication=authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        ResponseCredentialsLoginDTO responseCredentialsLoginDTO =new ResponseCredentialsLoginDTO();
        UserEntity currentUser=this.userService.getUserByUserName(credentialsLoginDTO.getUsername());
        if (currentUser == null || currentUser.getAccountType() == null) {
            throw new UserExistsException("Invalid username or password");
        }
        if (!currentUser.getAccountType().name().equals("CREDENTIALS")) {
            throw new UserExistsException("Invalid username or password");
        }
        responseCredentialsLoginDTO = this.userService.responseCredentialsLogin(authentication, credentialsLoginDTO);
        return ResponseEntity.ok().body(responseCredentialsLoginDTO);
    }

    @PostMapping("/socials-login")
    public ResponseEntity<Object> socialsLogin(@RequestBody SocialsLoginDTO socialsLoginDTO) throws UserExistsException {
        if(socialsLoginDTO.getEmail()==null  || socialsLoginDTO.getAccountType()==null){
            throw new UserExistsException("Username or password or account type is empty !");
        }
        if (!this.userService.checkExistsByEmailOrUsername(socialsLoginDTO.getEmail(),socialsLoginDTO.getUsername())) {
            UserEntity user = new UserEntity();
            user.setEmail(socialsLoginDTO.getEmail());
            user.setAvatar(socialsLoginDTO.getAvatar());
            user.setFullname(socialsLoginDTO.getFullName());
            user.setUsername(socialsLoginDTO.getUsername());
            user.setAccountType(socialsLoginDTO.getAccountType());
            this.userService.createUser(user);
            return ResponseEntity.ok().body(this.userService.responseUserDTO(user));
        }

        Authentication authentication=new UsernamePasswordAuthenticationToken(socialsLoginDTO.getEmail(),null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        ResponseSocialsLoginDTO responseSocialsLoginDTO =new ResponseSocialsLoginDTO();
        ResponseSocialsLoginDTO.UserLogin userLogin=new ResponseSocialsLoginDTO.UserLogin();
        responseSocialsLoginDTO = this.userService.responseSocialsLogin(authentication,socialsLoginDTO);
        return ResponseEntity.ok().body(responseSocialsLoginDTO);
    }


}
