package com.controller;

import com.dto.request.ActiveOTPRequest;
import com.dto.request.UserRequest;
import com.dto.response.RestResponse;
import com.dto.response.UserResponse;
import com.entity.UserEntity;
import com.exception.NotFoundException;
import com.exception.UserRequestException;
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
public class UserController {

    UserService userService;
    OTPService otpService;
    ModelMapper modelMapper;

    // Post
    @PostMapping
    public ResponseEntity<RestResponse<UserResponse>> registerUser(@RequestBody @Valid UserRequest request, BindingResult bindingResult) throws UserRequestException, UserRequestException {
        UserResponse userResponse = userService.registerUser(request, bindingResult);
        UserEntity userEntity = modelMapper.map(userResponse, UserEntity.class);
        String message = otpService.generateOTP(userEntity, "Yêu cầu xác thực email.");
        RestResponse<UserResponse> responseRestResponse = RestResponse.<UserResponse>builder()
                .data(userResponse)
                .message(message)
                .statusCode(HttpStatus.CREATED.value())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(responseRestResponse);
    }

    // Active account
    @PostMapping("/active_account")
    public ResponseEntity<RestResponse<String>> activeAccount(@RequestBody ActiveOTPRequest request) throws NotFoundException {
        String message = otpService.checkToken(request);
        RestResponse<String> restResponse = RestResponse.<String>builder()
                .data(message)
                .message("Kích hoạt tài khoản!")
                .statusCode(HttpStatus.OK.value())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(restResponse);
    }

}