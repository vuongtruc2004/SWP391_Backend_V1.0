package com.controller.api.v1;

<<<<<<< HEAD:SWP391_Backend/src/main/java/com/controller/api/v1/CourseController.java
import com.dto.response.ApiResponse;
import com.entity.CourseEntity;
import com.entity.UserEntity;
import com.exception.custom.UserRequestException;
import com.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
=======
import com.dto.ResultPaginationDTO;
import com.entity.CourseEntity;
import com.service.CourseService;
import com.turkraft.springfilter.boot.Filter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
>>>>>>> origin/login:src/main/java/com/controller/CourseController.java
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseController {
<<<<<<< HEAD:SWP391_Backend/src/main/java/com/controller/api/v1/CourseController.java
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
=======
    private final CourseService courseService;
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }
    @GetMapping("/courses")
    public ResponseEntity<ResultPaginationDTO> getAllCourses(
            @Filter Specification<CourseEntity> specification,
            Pageable pageable
    ) {
        return ResponseEntity.ok().body(this.courseService.findAllCourses(specification,pageable));
>>>>>>> origin/login:src/main/java/com/controller/CourseController.java
    }
}
