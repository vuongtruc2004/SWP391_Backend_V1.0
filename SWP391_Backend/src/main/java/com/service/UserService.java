package com.service;

import com.dto.request.RegisterRequest;
import com.dto.request.UpdateUserRequest;
import com.dto.request.UserRequest;
import com.dto.response.ApiResponse;
import com.dto.response.PageDetailsResponse;
import com.dto.response.UserResponse;
import com.entity.OTPEntity;
import com.entity.RoleEntity;
import com.entity.UserEntity;
import com.exception.custom.NotFoundException;
import com.exception.custom.RoleException;
import com.exception.custom.StorageException;
import com.exception.custom.UserException;
import com.helper.UserServiceHelper;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;
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
    private final FileService fileService;
    private final UserServiceHelper userServiceHelper;

    public UserService(UserRepository userRepository, ModelMapper modelMapper, RoleRepository roleRepository, PasswordEncoder passwordEncoder, OTPService otpService, OTPRepository otpRepository, FileService fileService, UserServiceHelper userServiceHelper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.otpService = otpService;
        this.otpRepository = otpRepository;
        this.fileService = fileService;
        this.userServiceHelper = userServiceHelper;
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
    public boolean lockUser(Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy người dùng"));
        if(user.getRole().getRoleName().equals(RoleNameEnum.ADMIN)) {
            throw new UserException("Không được khóa ADMIN");
        }
        if(Boolean.TRUE.equals(user.getLocked())) {
            user.setLocked(false);
        }else{
            user.setLocked(true);
        }
        return userRepository.save(user).getLocked();
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
        user.setAccountType(AccountTypeEnum.CREDENTIALS);
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
        UserEntity user = userRepository.findById(request.getUserId())
                .orElseThrow(()-> new UserException("Không tìm thấy người dùng"));
        user.setRole(roleEntity);
        user.setDob(request.getDob());
        user.setAvatar(request.getAvatar());
        user.setGender(request.getGender());
        user.setFullname(request.getFullname());
        UserEntity savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserResponse.class);
    }

    public UserResponse updateUserProfile(UpdateUserRequest updateUserRequest) {
        UserEntity userEntity = userRepository.findById(updateUserRequest.getUserId())
                .orElseThrow(() -> new NotFoundException("ID không tồn tại!"));
        userEntity.setFullname(updateUserRequest.getFullname());
        userEntity.setDob(updateUserRequest.getDob());
        userEntity.setGender(updateUserRequest.getGender());
        return modelMapper.map(userRepository.save(userEntity), UserResponse.class);
    }

    public UserResponse getUserProFile() {
        UserEntity userEntity = userServiceHelper.extractFromToken();
        UserResponse userResponse = modelMapper.map(userEntity, UserResponse.class);
        userResponse.setRoleName(userEntity.getRole().getRoleName());
        return userResponse;
    }

    public UserResponse updateAvatar(MultipartFile file, String folder) throws URISyntaxException, IOException {
        ApiResponse<String> fileResponse = fileService.uploadImage(file, folder);
        if (fileResponse.getStatus() == 200) {
            UserEntity userEntity = userServiceHelper.extractFromToken();
            userEntity.setAvatar(fileResponse.getData());
            return modelMapper.map(userRepository.save(userEntity), UserResponse.class);
        } else {
            throw new StorageException("Không tìm thấy ảnh");
        }
    }
}