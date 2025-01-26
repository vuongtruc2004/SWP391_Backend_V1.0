package com.service;

import com.dto.request.UserRequest;
import com.dto.response.UserResponse;
import com.entity.RoleEntity;
import com.entity.UserEntity;
import com.exception.custom.UserRequestException;
import com.repository.RoleRepository;
import com.repository.UserRepository;
import com.util.enums.AccountTypeEnum;
import com.util.enums.RoleNameEnum;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    RoleRepository roleRepository;
    ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

    @Transactional
    public UserResponse registerUser(UserRequest request, BindingResult bindingResult) throws UserRequestException {
        StringBuilder errorMessage = new StringBuilder();
        if (!bindingResult.getAllErrors().isEmpty()) {
            Map<String, String> errors = bindingResult.getFieldErrors()
                    .stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            for (Map.Entry<String, String> error : errors.entrySet()) {
                errorMessage.append(error.getKey()).append(":").append(error.getValue()).append(";");
            }
        }
        if (userRepository.existsByUsername(request.getUsername())) {
            errorMessage.append("username:").append("Username already exists!").append(";");
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            errorMessage.append("email:").append("Email already exists!").append(";");
        }
        if (!errorMessage.toString().isBlank()) {
            throw new UserRequestException(errorMessage.toString());
        }
        RoleEntity roleEntity = roleRepository.findByRoleName(RoleNameEnum.USER).orElse(null);
        UserEntity user = modelMapper.map(request, UserEntity.class);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setAccountType(AccountTypeEnum.CREDENTIALS);
        user.setRole(roleEntity);
        UserEntity savedUser = userRepository.save(user);
        UserResponse userResponse = modelMapper.map(savedUser, UserResponse.class);

        return userResponse;
    }

    public Optional<UserEntity> getUserByUserId(Long id) throws UserRequestException {
        return userRepository.findById(id);
    }

}