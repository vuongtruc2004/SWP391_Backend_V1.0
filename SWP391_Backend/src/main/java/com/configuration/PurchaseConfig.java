package com.configuration;

import com.entity.OrderEntity;
import com.repository.OrderRepository;
import com.util.enums.OrderStatusEnum;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PurchaseConfig {

    private static final Logger log = LoggerFactory.getLogger(PurchaseConfig.class);
    private final OrderRepository orderRepository;

    @Scheduled(fixedRate = 3000000)
    @Transactional
    public void removeAllExpiredOrders() {
        Instant now = Instant.now();
        List<OrderEntity> orderEntityList = orderRepository.findAllByOrderStatusNotAndExpiredAtLessThan(OrderStatusEnum.COMPLETED, now);
        orderRepository.deleteAll(orderEntityList);
        if (orderEntityList.isEmpty()) {
            log.info("Không có hóa đơn nào quá hạn!");
        } else {
            log.info("Đã xóa " + orderEntityList.size() + " hóa đơn quá hạn!");
        }
    }

}
