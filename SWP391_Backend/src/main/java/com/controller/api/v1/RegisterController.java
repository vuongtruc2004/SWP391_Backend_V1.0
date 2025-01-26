package com.controller.api.v1;

import com.dto.request.ActiveOTPRequest;
import com.dto.request.UserRequest;
import com.dto.response.ApiResponse;
import com.dto.response.UserResponse;
import com.entity.UserEntity;
import com.exception.custom.NotFoundException;
import com.exception.custom.UserRequestException;
import com.service.OTPService;
import com.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/register")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RegisterController {

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
    }

}