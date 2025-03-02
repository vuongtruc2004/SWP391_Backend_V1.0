package com.configuration;

import com.entity.OrderEntity;
import com.repository.OrderRepository;
import com.util.enums.OrderStatusEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PurchaseConfig {

    private final OrderRepository orderRepository;

    @Scheduled(fixedRate = 3000000)
    @Transactional
    public void removeAllExpiredOrders() {
        Instant now = Instant.now();
        List<OrderEntity> orderEntityList = orderRepository.findAllByOrderStatusAndExpiredAtLessThan(OrderStatusEnum.PENDING, now);
        orderRepository.deleteAll(orderEntityList);
        if (orderEntityList.isEmpty()) {
            System.out.println("Không có hóa đơn nào quá hạn!");
        } else {
            System.out.println("Đã xóa " + orderEntityList.size() + " hóa đơn quá hạn!");
        }
    }

}
