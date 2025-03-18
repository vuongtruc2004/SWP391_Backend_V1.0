package com.repository;


import com.entity.CouponEntity;
import com.repository.custom.JpaSpecificationRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;


public interface CouponRepository extends JpaSpecificationRepository<CouponEntity, Long> {
    Boolean existsByCouponCode(String code);

    @Query("select c from CouponEntity c " +
            "where c.endTime >= :now " +
            "and (c.maxUses is null or c.maxUses > c.usedCount)")
    List<CouponEntity> getAllCouponsAvailable(@Param("now") Instant now);

    List<CouponEntity> findByEndTimeBefore(Instant instant);
}
