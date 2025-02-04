package com.service;

import com.dto.request.RegisterRequest;
import com.dto.response.ApiResponse;
import com.entity.OTPEntity;
import com.entity.RoleEntity;
import com.entity.UserEntity;
import com.exception.custom.RoleException;
import com.exception.custom.UserException;
import com.repository.OTPRepository;
import com.repository.RoleRepository;
import com.repository.UserRepository;
import com.util.BuildResponse;
import com.util.enums.AccountTypeEnum;
import com.util.enums.RoleNameEnum;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final OTPService otpService;
    private final OTPRepository otpRepository;

    public UserService(UserRepository userRepository, ModelMapper modelMapper, RoleRepository roleRepository, PasswordEncoder passwordEncoder, OTPService otpService, OTPRepository otpRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.otpService = otpService;
        this.otpRepository = otpRepository;
    }

    public ApiResponse<Void> sendRegisterRequest(RegisterRequest registerRequest) {
        Optional<UserEntity> optionalUserEntity = userRepository.findByEmailAndAccountType(registerRequest.getEmail(), AccountTypeEnum.CREDENTIALS);
        if (optionalUserEntity.isPresent()) {
            UserEntity existedUserEntity = optionalUserEntity.get();
            if (Boolean.TRUE.equals(existedUserEntity.getActive())) {
                throw new UserException("Email này đã được sử dụng!");
            } else {
                OTPEntity otpEntity = existedUserEntity.getOtp();
                existedUserEntity.setOtp(null);
                UserEntity savedUserEntity = userRepository.save(existedUserEntity);
                otpRepository.delete(otpEntity);
                userRepository.delete(savedUserEntity);
            }
        }

        RoleEntity roleEntity = roleRepository.findByRoleName(RoleNameEnum.USER)
                .orElseThrow(() -> new RoleException("Role not found!"));
        UserEntity userEntity = modelMapper.map(registerRequest, UserEntity.class);

        userEntity.setAccountType(AccountTypeEnum.CREDENTIALS);
        userEntity.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        userEntity.setRole(roleEntity);
        userEntity.setActive(false);

        UserEntity savedUser = userRepository.save(userEntity);
        otpService.generateOTP(savedUser, "Yêu cầu xác thực email!");
        return BuildResponse.buildApiResponse(
                200,
                "Vui lòng kiểm tra email của ban!",
                null,
                null
        );
    }
}