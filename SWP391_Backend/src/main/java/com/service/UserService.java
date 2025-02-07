package com.service;

import com.dto.request.RegisterRequest;
import com.dto.request.UserRequest;
import com.dto.response.ApiResponse;
import com.dto.response.PageDetailsResponse;
import com.dto.response.UserResponse;
import com.entity.OTPEntity;
import com.entity.RoleEntity;
import com.entity.UserEntity;
import com.exception.custom.NotFoundException;
import com.exception.custom.RoleException;
import com.exception.custom.UserException;
import com.repository.OTPRepository;
import com.repository.RoleRepository;
import com.repository.UserRepository;
import com.service.auth.JwtService;
import com.util.BuildResponse;
import com.util.enums.AccountTypeEnum;
import com.util.enums.RoleNameEnum;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public UserResponse getUserById(Long id) {
        String email = JwtService.extractUsernameFromToken()
                .orElseThrow(() -> new NotFoundException("Email không tìm thấy"));

        String accountType = JwtService.extractAccountTypeFromToken()
                .orElseThrow(() -> new NotFoundException("Account type không tìm thấy"));

        UserEntity loginUser = userRepository.findByEmailAndAccountType(email, AccountTypeEnum.valueOf(accountType))
                .orElseThrow(() -> new NotFoundException("Vui lòng đăng nhập"));

        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy người dùng"));

        if (!loginUser.getRole().getRoleName().equals(RoleNameEnum.ADMIN) && Boolean.TRUE.equals(user.getLocked())) {
            throw new NotFoundException("Không tìm thấy người dùng");
        }

        UserResponse userResponse = modelMapper.map(user, UserResponse.class);
        userResponse.setRoleName(user.getRole().getRoleName());
        return userResponse;

    }

    public PageDetailsResponse<List<UserResponse>> getUserWithFilter(
            Pageable pageable, Specification<UserEntity> specification) {

        Page<UserEntity> page = userRepository.findAll(specification, pageable);
        List<UserResponse> userResponses = page.getContent()
                .stream().map(userEntity -> {
                    UserResponse userResponse = modelMapper.map(userEntity, UserResponse.class);
                    userResponse.setRoleName(userEntity.getRole().getRoleName());
                    return userResponse;
                })
                .toList();

        return BuildResponse.buildPageDetailsResponse(
                page.getNumber() + 1,
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements(),
                userResponses
        );
    }

    public void lockUser(Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy người dùng"));
        user.setLocked(true);
        userRepository.save(user);
    }

    public UserResponse createUser(UserRequest request) {
        if (request.getUserId() != null) {
            throw new UserException("Không được để id");
        }
        RoleEntity roleEntity = roleRepository.findByRoleName(request.getRoleName())
                .orElseThrow(() -> new RoleException("Role không tìm thấy"));
        if (userRepository.existsByEmailAndAccountType(request.getEmail(), AccountTypeEnum.CREDENTIALS)) {
            throw new UserException("Email đã được sử dụng");
        }
        UserEntity user = modelMapper.map(request, UserEntity.class);
        user.setRole(roleEntity);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        UserEntity savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserResponse.class);
    }

    public UserResponse updateUser(UserRequest request) {
        if (request.getUserId() == null) {
            throw new UserException("Cập nhật người dùng phải có id");
        }
        RoleEntity roleEntity = roleRepository.findByRoleName(request.getRoleName())
                .orElseThrow(() -> new RoleException("Role không tìm thấy"));
        if (userRepository.existsByEmailAndAccountTypeAndUserIdNot(request.getEmail(), AccountTypeEnum.CREDENTIALS, request.getUserId())) {
            throw new UserException("Email đã được sử dụng");
        }
        UserEntity user = modelMapper.map(request, UserEntity.class);
        user.setRole(roleEntity);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        UserEntity savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserResponse.class);
    }

    public UserResponse getUserProFile(){
        String email = JwtService.extractUsernameFromToken()
                .orElseThrow(() -> new NotFoundException("Email không tìm thấy"));

        String accountType = JwtService.extractAccountTypeFromToken()
                .orElseThrow(() -> new NotFoundException("Account type không tìm thấy"));

        UserEntity userEntity = userRepository.findByEmailAndAccountType(email, AccountTypeEnum.valueOf(accountType))
                .orElseThrow(() -> new NotFoundException("Không tìm thấy người dùng"));
        if(Boolean.TRUE.equals(userEntity.getLocked())){
            throw new UserException("Không tìm thấy");
        }
        UserResponse userResponse = modelMapper.map(userEntity, UserResponse.class);
        userResponse.setRoleName(userEntity.getRole().getRoleName());
        return userResponse;

    }
}