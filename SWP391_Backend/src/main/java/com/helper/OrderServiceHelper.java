package com.helper;

import com.dto.response.OrderResponse;
import com.dto.response.UserResponse;
import com.entity.OrderEntity;
import com.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderServiceHelper {
    private final ModelMapper modelMapper;
    private final CourseRepository courseRepository;

    public OrderResponse convertToOrderResponse(OrderEntity orderEntity) {
        OrderResponse orderResponse = modelMapper.map(orderEntity, OrderResponse.class);
        orderResponse.setUser(modelMapper.map(orderEntity.getUser(), UserResponse.class));
        return orderResponse;
    }
}
