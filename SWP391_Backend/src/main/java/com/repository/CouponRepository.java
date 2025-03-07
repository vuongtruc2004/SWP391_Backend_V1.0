package com.repository;


import com.entity.CouponEntity;
import com.repository.custom.JpaSpecificationRepository;
import org.springframework.stereotype.Repository;


public interface CouponRepository extends JpaSpecificationRepository<CouponEntity, Long> {
    Boolean existsByCouponCode(String code);
}
