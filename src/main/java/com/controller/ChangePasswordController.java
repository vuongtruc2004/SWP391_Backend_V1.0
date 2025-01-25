package com.controller;

import com.dto.request.EmailRequest;
import com.dto.request.OTPRequest;
import com.dto.response.ApiResponse;
import com.dto.response.RestResponse;
import com.entity.UserEntity;
import com.exception.AppException;
import com.exception.NotFoundException;
import com.repository.UserRepository;
import com.service.EmailSenderService;
import com.service.OTPService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChangePasswordController {
    private final EmailSenderService emailSenderService;
    private final UserRepository userRepository;
    private final OTPService otpService;

    @PostMapping("/send_otp")
    public RestResponse sendOtp(@RequestBody @Valid  EmailRequest request) throws NotFoundException {
        UserEntity user = userRepository.findByEmail(request.getEmail());
        if (user == null) {
            throw new NotFoundException("Email không tồn tại.");
        }
        String message = otpService.generateOTP(user, "Yêu cầu đổi mật khẩu.");
        return RestResponse.builder()
                .statusCode(HttpStatus.ACCEPTED.value())
                .message(message)
                .build();
    }

    @PostMapping("/change-password")
    public RestResponse changePassword(@RequestBody @Valid OTPRequest request) {
        String message = otpService.changePassword(request);
        return RestResponse.builder()
                .statusCode(HttpStatus.ACCEPTED.value())
                .message(message)
                .build();
    }

}