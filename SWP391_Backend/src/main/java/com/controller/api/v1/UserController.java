package com.controller.api.v1;

import com.dto.request.ChangePasswordRequest;
import com.dto.request.EmailRequest;
import com.dto.request.RegisterRequest;
import com.dto.request.UserRequest;
import com.dto.response.ApiResponse;
import com.dto.response.ExpertResponse;
import com.dto.response.PageDetailsResponse;
import com.dto.response.UserResponse;
import com.entity.UserEntity;
import com.exception.custom.NotFoundException;
import com.service.ExpertService;
import com.service.OTPService;
import com.service.UserService;
import com.turkraft.springfilter.boot.Filter;
import com.util.annotation.ApiMessage;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;
    private final OTPService otpService;
    private final ExpertService expertService;

    public UserController(UserService userService, OTPService otpService,
                          ExpertService expertService) {
        this.userService = userService;
        this.otpService = otpService;
        this.expertService = expertService;
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

    @ApiMessage("Lấy tất cả chuyên gia thành công !")
    @GetMapping("/experts")
    public ResponseEntity<PageDetailsResponse<List<ExpertResponse>>> getExperts(
            Pageable pageable
    ) {
        return ResponseEntity.ok(expertService.getExperts(pageable));
    }

    @ApiMessage("Lấy người dùng thành công")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @ApiMessage("Lấy tất cả người dùng thành công")
    @GetMapping
    public ResponseEntity<PageDetailsResponse<List<UserResponse>>> getUserWithFilter(
            Pageable pageable, @Filter Specification<UserEntity> specification) {
        return ResponseEntity.ok(userService.getUserWithFilter(pageable, specification));
    }

    @ApiMessage("Khóa người dùng thành công")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> lockUser(@PathVariable Long id) {
        userService.lockUser(id);
        return ResponseEntity.ok().build();
    }

    @ApiMessage("Tạo người dùng thành công")
    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserRequest userRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userRequest));
    }

    @ApiMessage("Cập nhật người dùng thành công")
    @PutMapping
    public ResponseEntity<UserResponse> updateUser(@RequestBody UserRequest userRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.updateUser(userRequest));
    }


}