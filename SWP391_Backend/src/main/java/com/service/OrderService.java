package com.service;

import com.dto.request.CourseOrderRequest;
import com.dto.request.OrderRequest;
import com.entity.CourseEntity;
import com.entity.OrderDetailsEntity;
import com.entity.OrderEntity;
import com.entity.UserEntity;
import com.exception.custom.NotFoundException;
import com.repository.CourseRepository;
import com.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    public void createOrder(OrderRequest orderRequest) {
        UserEntity userEntity = userRepository.findById(orderRequest.getUserId())
                .orElseThrow(() -> new NotFoundException("Người dùng không tồn tại!"));
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setUser(userEntity);
        for (CourseOrderRequest courseOrder : orderRequest.getCourseOrders()) {
            CourseEntity courseEntity = courseRepository.findCourseByIdWithFilter(courseOrder.getCourseId(), orderRequest.getUserId())
                    .orElseThrow()
            OrderDetailsEntity orderDetailsEntity = OrderDetailsEntity.builder()
                    .order(orderEntity)
                    .price(courseOrder.getSalePrice())
                    .course()
                    .build();
        }
    }
}
