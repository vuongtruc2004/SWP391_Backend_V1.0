package com.repository;

import com.entity.OrderDetailsEntity;
import com.repository.custom.JpaSpecificationRepository;

public interface OrderDetailsRepository extends JpaSpecificationRepository<OrderDetailsEntity, Long> {
}
