package com.controller.api.v1;

import com.dto.response.ApiResponse;
import com.entity.CourseEntity;
import com.entity.UserEntity;
import com.exception.UserRequestException;
import com.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class CourseController {
    private final UserService userService;

    @GetMapping("/my-course/{id}")
    public ResponseEntity<ApiResponse<List<CourseEntity>>> getAllCoursesOfUser(@PathVariable Long id) throws UserRequestException {
        Optional<UserEntity> user = userService.getUserByUserId(id);
        List<CourseEntity> list = user.get().getPurchasedCourses();
        ApiResponse<List<CourseEntity>> response = ApiResponse.<List<CourseEntity>>builder()
                .status(HttpStatus.CREATED.value())
                .data(list)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
