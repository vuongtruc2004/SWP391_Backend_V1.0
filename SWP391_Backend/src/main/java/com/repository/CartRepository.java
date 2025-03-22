package com.repository;

import com.entity.CartEntity;
import com.repository.custom.JpaSpecificationRepository;

public interface CartRepository extends JpaSpecificationRepository<CartEntity, Long> {
}
