package com.controller.api.v1;

import com.dto.request.ChangePasswordRequest;
import com.dto.request.EmailRequest;
import com.dto.request.UserRequest;
import com.dto.response.ApiResponse;
import com.dto.response.UserResponse;
import com.exception.custom.NotFoundException;
import com.exception.custom.UserRequestException;
import com.service.OTPService;
import com.service.UserService;
import com.util.annotation.ApiMessage;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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

    @ApiMessage("Vui lòng kiểm tra email của bạn!")
    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody @Valid UserRequest request, BindingResult bindingResult) throws UserRequestException {
        UserResponse userResponse = userService.register(request, bindingResult);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
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