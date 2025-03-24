package com.helper;

import com.dto.response.OrderResponse;
import com.dto.response.UserResponse;
import com.entity.CouponEntity;
import com.entity.OrderEntity;
import com.repository.CouponRepository;
import com.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class OrderServiceHelper {
    private final ModelMapper modelMapper;
    private final CouponRepository couponRepository;
    private final OrderRepository orderRepository;

    public OrderResponse convertToOrderResponse(OrderEntity orderEntity) {
        OrderResponse orderResponse = modelMapper.map(orderEntity, OrderResponse.class);
        orderResponse.setUser(modelMapper.map(orderEntity.getUser(), UserResponse.class));
        return orderResponse;
    }

    public void returnCouponThenDeleteOrder(OrderEntity orderEntity) {
        CouponEntity usingCoupon = orderEntity.getCoupon();
        if (usingCoupon != null && usingCoupon.getEndTime().isAfter(Instant.now())) {
            usingCoupon.setUsedCount(usingCoupon.getUsedCount() - 1);
            couponRepository.save(usingCoupon);
        }
        orderRepository.delete(orderEntity);
    }
}
