package com.helper;

import com.entity.UserEntity;
import com.exception.custom.NotFoundException;
import com.exception.custom.UserException;
import com.repository.UserRepository;
import com.service.auth.JwtService;
import com.util.enums.AccountTypeEnum;
import org.springframework.stereotype.Service;

@Service
public class UserServiceHelper {

    private final UserRepository userRepository;

    public UserServiceHelper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity extractFromToken() {
        String email = JwtService.extractUsernameFromToken()
                .orElseThrow(() -> new NotFoundException("Email không tìm thấy"));

        String accountType = JwtService.extractAccountTypeFromToken()
                .orElseThrow(() -> new NotFoundException("Account type không tìm thấy"));

        UserEntity userEntity = userRepository.findByEmailAndAccountType(email, AccountTypeEnum.valueOf(accountType))
                .orElseThrow(() -> new NotFoundException("Không tìm thấy người dùng"));
        if (Boolean.TRUE.equals(userEntity.getLocked())) {
            throw new UserException("Không tìm thấy");
        }
        return userEntity;
    }
}
