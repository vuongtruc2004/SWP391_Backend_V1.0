package com.controller.api.v1;

import com.dto.request.StorageCourseRequest;
import com.dto.response.CartResponse;
import com.service.CartService;
import com.util.annotation.ApiMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("api/v1/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @ApiMessage("Lấy giỏ hàng của người dùng thành công!")
    @PostMapping
    public ResponseEntity<CartResponse> saveAndGetUserCart(@RequestBody Set<StorageCourseRequest> storageCourses) {
        return ResponseEntity.ok(cartService.saveAndGetUserCart(storageCourses));
    }

    @ApiMessage("Thêm khóa học vào giỏ hàng thành công!")
    @PostMapping("/add")
    public ResponseEntity<Void> addCourseToUserCart(@RequestBody StorageCourseRequest storageCourseRequest) {
        cartService.addCourseToUserCart(storageCourseRequest);
        return ResponseEntity.ok().build();
    }

    @ApiMessage("Xóa khóa học trong giỏ hàng thành công!")
    @DeleteMapping
    public ResponseEntity<Void> deleteCoursesFromUserCart(@RequestParam Set<Long> courseIds) {
        cartService.deleteCoursesFromUserCart(courseIds);
        return ResponseEntity.ok().build();
    }
}
