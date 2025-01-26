package com.controller.api.v1;

import com.dto.response.CourseResponse;
import com.dto.response.PageDetailsResponse;
import com.entity.CourseEntity;
import com.entity.UserEntity;
import com.exception.custom.UserRequestException;
import com.service.CourseService;
import com.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/my-course")
public class CourseController {
    private final UserService userService;
    private final CourseService courseService;
    @GetMapping("/{id}")
    public ResponseEntity<PageDetailsResponse<List<CourseResponse>>> getAllCoursesOfUser(@PathVariable Long id, Pageable pageable) throws UserRequestException {
        return ResponseEntity.ok(courseService.getAllCoursesByUser(id, pageable));
    }
    
}
