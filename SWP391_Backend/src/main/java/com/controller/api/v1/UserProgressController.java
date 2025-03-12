package com.controller.api.v1;

import com.dto.request.UserProgressRequest;
import com.dto.response.UserProgressResponse;
import com.service.UserProgressService;
import com.util.annotation.ApiMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/progresses")
@RequiredArgsConstructor
public class UserProgressController {

    private final UserProgressService userProgressService;

    @ApiMessage("Lấy tiến độ của người dùng thành công")
    @GetMapping
    public ResponseEntity<Set<UserProgressResponse>> getUserProgresses() {
        return ResponseEntity.ok(userProgressService.getUserProgresses());
    }

    @ApiMessage("Cập nhật tiến độ thành công!")
    @PostMapping
    public ResponseEntity<UserProgressResponse> changeStatus(@RequestBody UserProgressRequest userProgressRequest) {
        return ResponseEntity.ok(userProgressService.changeStatus(userProgressRequest));
    }
}
