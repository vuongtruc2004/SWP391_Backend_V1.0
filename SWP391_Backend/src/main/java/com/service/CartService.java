package com.service;

import com.dto.request.StorageCourseRequest;
import com.dto.response.CartResponse;
import com.entity.CartCourseEntity;
import com.entity.CartEntity;
import com.entity.CourseEntity;
import com.entity.UserEntity;
import com.exception.custom.NotFoundException;
import com.exception.custom.UserException;
import com.helper.CartServiceHelper;
import com.helper.UserServiceHelper;
import com.repository.CartCourseRepository;
import com.repository.CartRepository;
import com.repository.CourseRepository;
import com.repository.UserRepository;
import com.util.enums.CartCourseStatus;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CartService {

    private final UserServiceHelper userServiceHelper;
    private final CourseRepository courseRepository;
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final CartServiceHelper cartServiceHelper;
    private final CartCourseRepository cartCourseRepository;

    public CartResponse saveAndGetUserCart(Set<StorageCourseRequest> storageCourses) {
        UserEntity user = userServiceHelper.extractUserFromToken();
        if (user == null) {
            throw new UserException("Vui lòng đăng nhập để thực hiện chức năng này!");
        }

        CartEntity cart = user.getCart();
        if (cart == null) {
            cart = CartEntity.builder().user(user).cartCourses(new ArrayList<>()).build();
        }

        List<CartCourseEntity> currentCoursesInCart = new ArrayList<>(cart.getCartCourses());
        Set<CourseEntity> purchasedCourses = user.getCourses() == null ? new HashSet<>() : new HashSet<>(user.getCourses());

        for (StorageCourseRequest storageCourseRequest : storageCourses) {
            CourseEntity course = courseRepository.findByCourseIdAndAcceptedTrue(storageCourseRequest.getCourseId())
                    .orElseThrow(() -> new NotFoundException("Khóa học không tồn tại!"));

            if (!cartServiceHelper.isExistedCourseInCart(course.getCourseId(), currentCoursesInCart) && !purchasedCourses.contains(course)) {
                CartCourseEntity cartCourse = CartCourseEntity.builder()
                        .course(course)
                        .cart(cart)
                        .status(storageCourseRequest.getStatus() == null ? CartCourseStatus.NOW : storageCourseRequest.getStatus())
                        .build();
                currentCoursesInCart.add(cartCourse);
            }
        }

        cart.setCartCourses(currentCoursesInCart);
        CartEntity newCart = cartRepository.save(cart);
        user.setCart(newCart);
        userRepository.save(user);

        return modelMapper.map(newCart, CartResponse.class);
    }

    public void addCourseToUserCart(StorageCourseRequest storageCourseRequest) {
        UserEntity user = userServiceHelper.extractUserFromToken();
        if (user == null) {
            throw new UserException("Vui lòng đăng nhập để thực hiện chức năng này!");
        }
        CourseEntity course = courseRepository.findByCourseIdAndAcceptedTrue(storageCourseRequest.getCourseId())
                .orElseThrow(() -> new NotFoundException("Khóa học không tồn tại!"));

        CartEntity cart = user.getCart();
        if (cart == null) {
            cart = CartEntity.builder().user(user).cartCourses(new ArrayList<>()).build();
        }

        if (!cartServiceHelper.isExistedCourseInCart(course.getCourseId(), cart.getCartCourses())) {
            cart.getCartCourses().add(CartCourseEntity.builder()
                    .course(course)
                    .cart(cart)
                    .status(storageCourseRequest.getStatus() == null ? CartCourseStatus.NOW : storageCourseRequest.getStatus())
                    .build());
        }

        CartEntity newCart = cartRepository.save(cart);
        user.setCart(newCart);
        userRepository.save(user);
    }

    public void deleteCoursesFromUserCart(Set<Long> courseIds) {
        UserEntity user = userServiceHelper.extractUserFromToken();
        if (user == null) {
            throw new UserException("Vui lòng đăng nhập để thực hiện chức năng này!");
        }
        CartEntity cart = user.getCart();
        if (cart != null && cart.getCartCourses() != null) {
            for (Long courseId : courseIds) {
                CourseEntity course = courseRepository.findByCourseIdAndAcceptedTrue(courseId)
                        .orElseThrow(() -> new NotFoundException("Khóa học không tồn tại!"));

                CartCourseEntity cartCourseEntity = cartCourseRepository.findByCartAndCourse(cart, course)
                        .orElseThrow(() -> new NotFoundException("CartCourse không tồn tại!"));

                cartCourseRepository.delete(cartCourseEntity);
            }
        }
    }

    public void changeStatusOfCartCourse(Long courseId) {
        UserEntity user = userServiceHelper.extractUserFromToken();
        if (user == null) {
            throw new UserException("Vui lòng đăng nhập để thực hiện chức năng này!");
        }
        CartEntity cart = user.getCart();
        if (cart != null && cart.getCartCourses() != null) {
            CourseEntity course = courseRepository.findByCourseIdAndAcceptedTrue(courseId)
                    .orElseThrow(() -> new NotFoundException("Khóa học không tồn tại!"));

            CartCourseEntity cartCourseEntity = cartCourseRepository.findByCartAndCourse(cart, course)
                    .orElseThrow(() -> new NotFoundException("CartCourse không tồn tại!"));

            if (cartCourseEntity.getStatus().equals(CartCourseStatus.NOW)) {
                cartCourseEntity.setStatus(CartCourseStatus.LATER);
            } else {
                cartCourseEntity.setStatus(CartCourseStatus.NOW);
            }
            cartCourseRepository.save(cartCourseEntity);
        }
    }
}
