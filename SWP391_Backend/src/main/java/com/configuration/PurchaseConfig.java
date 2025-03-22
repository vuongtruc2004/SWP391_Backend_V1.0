package com.configuration;

import com.entity.OrderEntity;
import com.helper.OrderServiceHelper;
import com.repository.OrderRepository;
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
    private final OrderServiceHelper orderServiceHelper;

    @Scheduled(fixedRate = 3000000)
    @Transactional
    public void removeAllExpiredOrders() {
        List<OrderEntity> orderEntityList = orderRepository.findAllByPaidAtIsNullAndExpiredAtLessThanEqual(Instant.now());
        for (OrderEntity orderEntity : orderEntityList) {
            orderServiceHelper.returnCouponBeforeOrderDeleted(orderEntity);
        }
        if (orderEntityList.isEmpty()) {
            log.info("Không có hóa đơn nào hết hạn!");
        } else {
            log.info("Đã xóa " + orderEntityList.size() + " hóa đơn hết hạn!");
        }
    }

}
