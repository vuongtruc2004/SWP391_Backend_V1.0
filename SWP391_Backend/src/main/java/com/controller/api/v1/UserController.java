package com.controller.api.v1;

<<<<<<< HEAD:SWP391_Backend/src/main/java/com/controller/api/v1/UserController.java
import com.dto.request.ActiveOTPRequest;
import com.dto.request.UserRequest;
import com.dto.response.ApiResponse;
import com.dto.response.UserResponse;
import com.entity.UserEntity;
import com.exception.custom.NotFoundException;
import com.exception.custom.UserRequestException;
import com.service.OTPService;
=======
import com.entity.UserEntity;
>>>>>>> origin/login:src/main/java/com/controller/UserController.java
import com.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
<<<<<<< HEAD:SWP391_Backend/src/main/java/com/controller/api/v1/UserController.java

    UserService userService;
    OTPService otpService;
    ModelMapper modelMapper;

    // Post
    @PostMapping
    public ResponseEntity<ApiResponse<UserResponse>> registerUser(@RequestBody @Valid UserRequest request, BindingResult bindingResult) throws UserRequestException, UserRequestException {
        UserResponse userResponse = userService.registerUser(request, bindingResult);
        UserEntity userEntity = modelMapper.map(userResponse, UserEntity.class);
        String message = otpService.generateOTP(userEntity, "Yêu cầu xác thực email.");
        ApiResponse<UserResponse> apiResponse = ApiResponse.<UserResponse>builder()
                .status(HttpStatus.CREATED.value())
                .message(message)
                .data(userResponse)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    // Active account
    @PostMapping("/active_account")
    public ResponseEntity<ApiResponse<String>> activeAccount(@RequestBody ActiveOTPRequest request) throws NotFoundException {
        String message = otpService.checkToken(request);
        ApiResponse<String> errorResponse = ApiResponse.<String>builder()
                .data(message)
                .message("Kích hoạt tài khoản!")
                .status(HttpStatus.OK.value())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(errorResponse);
=======
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/user")
    public UserEntity createUser(@RequestBody UserEntity user) {
        String hashPassword =  passwordEncoder.encode(user.getPassword());
        user.setPassword(hashPassword);
        return userService.createUser(user);
>>>>>>> origin/login:src/main/java/com/controller/UserController.java
    }
}
