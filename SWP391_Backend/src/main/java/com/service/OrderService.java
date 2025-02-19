package com.service;

import com.dto.request.OrderRequest;
import com.dto.response.OrderResponse;
import com.entity.CourseEntity;
import com.entity.OrderDetailsEntity;
import com.entity.OrderEntity;
import com.entity.UserEntity;
import com.exception.custom.CourseException;
import com.exception.custom.NotFoundException;
import com.repository.CourseRepository;
import com.repository.OrderRepository;
import com.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

    public OrderResponse createOrder(OrderRequest orderRequest) {
        UserEntity userEntity = userRepository.findByUserIdAndLockedFalse(orderRequest.getUserId())
                .orElseThrow(() -> new NotFoundException("Người dùng không tồn tại!"));
        Set<CourseEntity> purchasedCourses = userRepository.getUserPurchaseCourses(userEntity.getUserId());

        OrderEntity orderEntity = new OrderEntity();
        Set<OrderDetailsEntity> orderDetailsEntities = new HashSet<>();
        orderEntity.setUser(userEntity);

        for (Long courseId : orderRequest.getCourseIds()) {
            CourseEntity courseEntity = courseRepository.findByCourseIdAndAcceptedTrue(courseId)
                    .orElseThrow(() -> new NotFoundException("Khóa học không tồn tại!"));
            if (purchasedCourses.contains(courseEntity)) {
                throw new CourseException("Bạn đã mua khóa học này rồi!");
            }
            OrderDetailsEntity orderDetailsEntity = OrderDetailsEntity.builder()
                    .order(orderEntity)
                    .price(courseEntity.getSalePrice())
                    .course(courseEntity)
                    .build();
            orderDetailsEntities.add(orderDetailsEntity);
        }
        orderEntity.setOrderDetails(orderDetailsEntities);
        OrderEntity newOrder = orderRepository.save(orderEntity);
        return modelMapper.map(newOrder, OrderResponse.class);
    }
}
