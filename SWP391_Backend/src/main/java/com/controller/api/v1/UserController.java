package com.controller.api.v1;

import com.dto.request.UserRequest;
import com.dto.response.UserResponse;
import com.exception.custom.NotFoundException;
import com.exception.custom.UserRequestException;
import com.service.OTPService;
import com.service.UserService;
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

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody @Valid UserRequest request, BindingResult bindingResult) throws UserRequestException {
        UserResponse userResponse = userService.register(request, bindingResult);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @GetMapping("/active")
    public ResponseEntity<String> activeAccount(@RequestParam(name = "code") String code) throws NotFoundException {
        String message = otpService.activeAccount(code);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }
}