package com.helper;

import com.entity.CartCourseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@RequiredArgsConstructor
public class CartServiceHelper {
    
    public boolean isExistedCourseInCart(Long courseId, Collection<CartCourseEntity> cartCourses) {
        return cartCourses.stream()
                .anyMatch(cartCourse -> cartCourse.getCourse().getCourseId().equals(courseId));
    }

}
