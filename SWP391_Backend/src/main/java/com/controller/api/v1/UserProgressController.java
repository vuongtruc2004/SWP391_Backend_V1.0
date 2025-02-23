package com.controller.api.v1;

import com.dto.request.UserProgressRequest;
import com.entity.UserProgressEntity;
import com.service.UserProgressService;
import com.util.annotation.ApiMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/progress")
@RequiredArgsConstructor
public class UserProgressController {

    private final UserProgressService userProgressService;

    @ApiMessage("Lấy tiến độ của người dùng thành công")
    @GetMapping
    public ResponseEntity<List<UserProgressEntity>> getUserProgressByCourseId(@RequestParam Long courseId) {
        return ResponseEntity.ok(userProgressService.getUserProgressByCourseId(courseId));
    }

    @ApiMessage("Cập nhật tiến độ thành công!")
    @PostMapping
    public ResponseEntity<UserProgressEntity> changeStatus(@RequestBody UserProgressRequest userProgressRequest) {
        return ResponseEntity.ok(userProgressService.changeStatus(userProgressRequest));
    }
}
