package com.repository;

import com.entity.OrderEntity;
import com.repository.custom.JpaSpecificationRepository;

public interface OrderRepository extends JpaSpecificationRepository<OrderEntity, Long> {
}
