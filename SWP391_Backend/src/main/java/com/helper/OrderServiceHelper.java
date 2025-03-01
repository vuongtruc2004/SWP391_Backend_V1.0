package com.helper;

import com.dto.response.OrderResponse;
import com.dto.response.UserResponse;
import com.entity.OrderDetailsEntity;
import com.entity.OrderEntity;
import com.entity.UserEntity;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceHelper {
    private final ModelMapper modelMapper;

    public OrderServiceHelper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    public OrderResponse convertToOrderResponse(OrderEntity orderEntity) {
        OrderResponse orderResponse = modelMapper.map(orderEntity, OrderResponse.class);
        orderResponse.setUser(modelMapper.map(orderEntity.getUser(), UserResponse.class));
        return orderResponse;
    }
}
