package com.service;

import com.dto.request.UserRequest;
import com.dto.response.UserResponse;
import com.entity.RoleEntity;
import com.entity.UserEntity;
import com.exception.custom.NotFoundException;
import com.exception.custom.UserRequestException;
import com.repository.RoleRepository;
import com.repository.UserRepository;
import com.util.enums.AccountTypeEnum;
import com.util.enums.RoleNameEnum;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final OTPService otpService;

    public UserService(UserRepository userRepository, ModelMapper modelMapper, RoleRepository roleRepository, PasswordEncoder passwordEncoder, OTPService otpService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.otpService = otpService;
    }

    @Transactional
    public UserResponse register(UserRequest userRequest, BindingResult bindingResult) throws UserRequestException {
        StringBuilder errorMessage = new StringBuilder();
        if (!bindingResult.getAllErrors().isEmpty()) {
            Map<String, String> errors = bindingResult.getFieldErrors()
                    .stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            for (Map.Entry<String, String> error : errors.entrySet()) {
                errorMessage.append(error.getKey()).append(":").append(error.getValue()).append(";");
            }
        }
        if (userRepository.existsByUsername(userRequest.getUsername())) {
            errorMessage.append("username:").append("Username already exists!").append(";");
        }
        if (userRepository.existsByEmailAndAccountType(userRequest.getEmail(), AccountTypeEnum.CREDENTIALS)) {
            errorMessage.append("email:").append("Email already exists!").append(";");
        }
        if (!errorMessage.toString().isBlank()) {
            throw new UserRequestException(errorMessage.toString());
        }

        RoleEntity roleEntity = roleRepository.findByRoleName(RoleNameEnum.USER)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy role: USER!"));

        UserEntity user = modelMapper.map(userRequest, UserEntity.class);
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setAccountType(AccountTypeEnum.CREDENTIALS);
        user.setRole(roleEntity);
        user.setActive(false);

        UserEntity savedUser = userRepository.save(user);
        otpService.generateOTP(savedUser, "Yêu cầu xác thực email!");
        return modelMapper.map(savedUser, UserResponse.class);
    }
}