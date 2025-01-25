package com.service;

import com.entity.UserEntity;
import com.repository.custom.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import java.util.Collections;


@Component("userDetailsService")
public class UserDetailsCustom implements UserDetailsService {
    private final UserRepository userRepository;
    public UserDetailsCustom(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity=this.userRepository.findByUsername(username);
        if(userEntity==null){
            throw new UsernameNotFoundException("User not found");
        }
        return new User(userEntity.getUsername(),userEntity.getPassword(), Collections.singletonList((new SimpleGrantedAuthority("USER"))));
    }
}
