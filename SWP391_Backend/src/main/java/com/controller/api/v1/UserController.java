package com.controller.api.v1;

import com.dto.request.ChangePasswordRequest;
import com.dto.request.EmailRequest;
import com.dto.request.RegisterRequest;
import com.dto.response.ApiResponse;
import com.exception.custom.NotFoundException;
import com.service.OTPService;
import com.service.UserService;
import com.util.annotation.ApiMessage;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;
    private final OTPService otpService;

    public UserController(UserService userService, OTPService otpService) {
        this.userService = userService;
        this.otpService = otpService;
    }

    @PostMapping("/request_register")
    public ResponseEntity<ApiResponse<Void>> sendRegisterRequest(@RequestBody @Valid RegisterRequest registerRequest) {
        return ResponseEntity.ok(userService.sendRegisterRequest(registerRequest));
    }

    @ApiMessage("Kích hoạt tài khoản thành công!")
    @GetMapping("/active")
    public ResponseEntity<ApiResponse<Void>> activeAccount(@RequestParam(name = "code") String code) throws NotFoundException {
        return ResponseEntity.ok(otpService.activeAccount(code));
    }

    @ApiMessage("Đổi mật khẩu thành công!")
    @PostMapping("/change_password")
    public ResponseEntity<ApiResponse<Void>> changePassword(@RequestBody @Valid ChangePasswordRequest changePasswordRequest) {
        return ResponseEntity.ok(otpService.changePassword(changePasswordRequest));
    }

    @ApiMessage("Vui lòng kiểm tra email của bạn!")
    @PostMapping("/request_change_password")
    public ResponseEntity<ApiResponse<Void>> sendChangePasswordRequest(@RequestBody @Valid EmailRequest emailRequest) {
        return ResponseEntity.ok(otpService.sendChangePasswordRequest(emailRequest.getEmail()));
    }
}