package com.controller.api.v1;

import com.dto.request.*;
import com.dto.response.*;
import com.dto.response.details.ExpertDetailsResponse;
import com.entity.UserEntity;
import com.exception.custom.NotFoundException;
import com.service.OTPService;
import com.service.UserService;
import com.turkraft.springfilter.boot.Filter;
import com.util.annotation.ApiMessage;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final OTPService otpService;

    @ApiMessage("Gửi yêu cầu đăng kí tài khoản thành công!")
    @PostMapping("/request_register")
    public ResponseEntity<ApiResponse<Void>> sendRegisterRequest(@RequestBody @Valid RegisterRequest registerRequest) {
        return ResponseEntity.ok(userService.sendRegisterRequest(registerRequest));
    }

    @ApiMessage("Kích hoạt tài khoản thành công!")
    @GetMapping("/active/{code}")
    public ResponseEntity<ApiResponse<Void>> activeAccount(@PathVariable String code) throws NotFoundException {
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

    @ApiMessage("Lấy người dùng thành công")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @ApiMessage("Lấy tất cả người dùng thành công")
    @GetMapping
    public ResponseEntity<PageDetailsResponse<List<UserResponse>>> getUsersWithFilter(
            Pageable pageable,
            @Filter Specification<UserEntity> specification) {
        return ResponseEntity.ok(userService.getUserWithFilter(pageable, specification));
    }

    @ApiMessage("Lấy tất cả người dùng ứng với khóa học thành công")
    @GetMapping("/course/{courseId}")
    public ResponseEntity<PageDetailsResponse<List<UserResponse>>> getUsersByCourse(
            Pageable pageable,
            @Filter Specification<UserEntity> specification, @PathVariable Long courseId) {
        return ResponseEntity.ok(userService.getUserByCourse(pageable, specification, courseId));
    }

    @ApiMessage("Khóa người dùng thành công")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> lockUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.lockUser(id));
    }

    @ApiMessage("Tạo người dùng thành công")
    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserRequest userRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userRequest));
    }

    @ApiMessage("Cập nhật người dùng thành công")
    @PutMapping("/profile")
    public ResponseEntity<UserResponse> updateUserProfile(@RequestBody UpdateUserRequest updateUserRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.updateUserProfile(updateUserRequest));
    }

    @ApiMessage("Cập nhật người dùng thành công")
    @PutMapping
    public ResponseEntity<UserResponse> updateUser(@RequestBody UserRequest userRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.updateUser(userRequest));
    }

    @ApiMessage("Lấy user profile thành công")
    @GetMapping("/profile")
    public ResponseEntity<UserResponse> getUserProfile() {
        return ResponseEntity.ok(userService.getUserProFile());
    }

    @ApiMessage("Cập nhật avatar thành công")
    @PostMapping("/avatar")
    public ResponseEntity<UserResponse> uploadAvatar(
            @RequestParam(name = "file", required = false) MultipartFile file,
            @RequestParam(name = "folder") String folder) throws URISyntaxException, IOException {
        return ResponseEntity.ok(userService.updateAvatar(file, folder));
    }

    @ApiMessage("Cập nhật avatar thành công")
    @PostMapping("/avataradmin")
    public ResponseEntity<UserResponse> uploadAvatarByAdmin(
            @RequestParam(name = "file", required = false) MultipartFile file,
            @RequestParam(name = "folder") String folder) throws URISyntaxException, IOException {
        return ResponseEntity.ok(userService.updateAvatarByAdmin(file, folder));
    }

    @ApiMessage("Đếm giới tính thành công!")
    @GetMapping("/gender_count")
    public ResponseEntity<GenderCountResponse> genderCount() throws NotFoundException {
        return ResponseEntity.ok(userService.genderCount());
    }

    @ApiMessage("Thống kê người học theo độ tuôỉ !")
    @GetMapping("/age_count")
    public ResponseEntity<Map<String, Long>> ageCount() throws NotFoundException {
        return ResponseEntity.ok(userService.getUserByAge());
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @ApiMessage("Lấy tất cả lịch sử mua hàng thành công!")
    @GetMapping("/purchase-history/{orderStatus}")
    public ResponseEntity<List<OrderResponse>> getUserPurchaseHistory(@PathVariable String orderStatus) {
        return ResponseEntity.ok(userService.getUserPurchaseHistory(orderStatus));
    }

    @ApiMessage("Lấy tất cả chuyên gia theo dõi thành công!")
    @GetMapping("/following-experts")
    public ResponseEntity<List<ExpertDetailsResponse>> getMyFollowingExperts() {
        return ResponseEntity.ok(userService.getMyFollowingExperts());
    }
}