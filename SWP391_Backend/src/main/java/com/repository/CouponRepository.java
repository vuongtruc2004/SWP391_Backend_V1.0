package com.repository;


import com.entity.CouponEntity;
import com.repository.custom.JpaSpecificationRepository;
import java.time.Instant;
import java.util.List;


public interface CouponRepository extends JpaSpecificationRepository<CouponEntity, Long> {
    Boolean existsByCouponCode(String code);
    List<CouponEntity> findByEndTimeBefore(Instant instant);



}
