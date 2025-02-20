package com.service;

import com.dto.request.OrderRequest;
import com.dto.response.*;
import com.entity.CourseEntity;
import com.entity.OrderDetailsEntity;
import com.entity.OrderEntity;
import com.entity.UserEntity;
import com.exception.custom.CourseException;
import com.exception.custom.NotFoundException;
import com.exception.custom.OrderException;
import com.repository.CourseRepository;
import com.repository.OrderRepository;
import com.repository.UserRepository;
import com.util.BuildResponse;
import com.util.enums.OrderStatusEnum;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final NotificationService notificationService;

    public OrderResponse createOrder(OrderRequest orderRequest) {
        UserEntity userEntity = userRepository.findByUserIdAndLockedFalse(orderRequest.getUserId())
                .orElseThrow(() -> new NotFoundException("Người dùng không tồn tại!"));
        Set<CourseEntity> purchasedCourses = userEntity.getCourses();

        OrderEntity orderEntity = new OrderEntity();
        Set<OrderDetailsEntity> orderDetailsEntities = new HashSet<>();
        orderEntity.setUserId(userEntity.getUserId());

        for (Long courseId : orderRequest.getCourseIds()) {
            CourseEntity courseEntity = courseRepository.findByCourseIdAndAcceptedTrue(courseId)
                    .orElseThrow(() -> new NotFoundException("Khóa học không tồn tại!"));
            if (purchasedCourses.contains(courseEntity)) {
                throw new CourseException("Bạn đã mua khóa học này rồi!");
            }
            OrderDetailsEntity orderDetailsEntity = OrderDetailsEntity.builder()
                    .order(orderEntity)
                    .price(courseEntity.getSalePrice())
                    .courseId(courseEntity.getCourseId())
                    .build();
            orderDetailsEntities.add(orderDetailsEntity);
        }
        orderEntity.setOrderDetails(orderDetailsEntities);
        OrderEntity newOrder = orderRepository.save(orderEntity);
        return modelMapper.map(newOrder, OrderResponse.class);
    }

    public OrderResponse activeCoursesForUser(Long orderId) {
        OrderEntity orderEntity = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Hóa đơn không tồn tại!"));

        if (orderEntity.getOrderStatus().equals(OrderStatusEnum.PENDING)) {
            UserEntity userEntity = userRepository.findByUserIdAndLockedFalse(orderEntity.getUserId())
                    .orElseThrow(() -> new NotFoundException("Người dùng không tồn tại!"));
            for (OrderDetailsEntity orderDetailsEntity : orderEntity.getOrderDetails()) {
                CourseEntity courseEntity = courseRepository.findByCourseIdAndAcceptedTrue(orderDetailsEntity.getCourseId())
                        .orElseThrow(() -> new NotFoundException("Khóa học không tồn tại!"));
                Set<UserEntity> currentRegister = courseEntity.getUsers();
                currentRegister.add(userEntity);
                courseEntity.setUsers(currentRegister);
                courseRepository.save(courseEntity);
            }
            orderEntity.setOrderStatus(OrderStatusEnum.COMPLETED);
            OrderEntity updatedOrder = orderRepository.save(orderEntity);
            notificationService.purchaseSuccessNotification(userEntity.getFullname());
            return modelMapper.map(updatedOrder, OrderResponse.class);
        } else {
            throw new OrderException("Bạn đã thanh toán hóa đơn này rồi!");
        }
    }

    public PageDetailsResponse<List<OrderResponse>> getOrdersWithFilters(
            Pageable pageable,
            Specification<OrderEntity> specification) {
        Page<OrderEntity> page = orderRepository.findAll(specification, pageable);
        List<OrderResponse> orderResponses = page.getContent()
                .stream()
                .map(orderEntity -> {
                    OrderResponse orderResponse = modelMapper.map(orderEntity, OrderResponse.class);
                    UserEntity userEntity = userRepository.findById(orderEntity.getUserId())
                            .orElseThrow(() -> new NotFoundException("Người dùng không tìm thấy")
                            );
                    orderResponse.setUser(modelMapper.map(userEntity, UserResponse.class));
                    Set<OrderDetailsResponse> orderDetailsResponses = orderEntity.getOrderDetails().stream()
                            .map(orderDetailsEntity -> {
                                OrderDetailsResponse orderDetailsResponse = modelMapper.map(orderDetailsEntity, OrderDetailsResponse.class);
                                CourseEntity courseEntity = courseRepository.findById(orderDetailsEntity.getCourseId())
                                        .orElseThrow(() -> new NotFoundException("Không tìm thấy khóa học"));
                                orderDetailsResponse.setCourse(modelMapper.map(courseEntity, CourseResponse.class));
                                return orderDetailsResponse;
                            })
                            .collect(Collectors.toSet());
                    orderResponse.setOrderDetails(orderDetailsResponses);
                    return orderResponse;
                })
                .toList();

        return BuildResponse.buildPageDetailsResponse(
                page.getNumber() + 1,
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements(),
                orderResponses);
    }
}
