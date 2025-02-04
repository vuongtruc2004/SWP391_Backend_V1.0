package com.service.auth;

import com.entity.UserEntity;
import com.exception.custom.UserException;
import com.repository.UserRepository;
import com.util.enums.AccountTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("userDetailsService")
public class UserDetailsCustom implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsCustom(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmailAndAccountType(email, AccountTypeEnum.CREDENTIALS)
                .orElseThrow(() -> new UserException("Sai tên tài khoản hoặc mật khẩu!"));

        return new User(
                userEntity.getEmail(),
                userEntity.getPassword(),
                List.of(new SimpleGrantedAuthority(userEntity.getRole().getRoleName().name()))
        );
    }
}
