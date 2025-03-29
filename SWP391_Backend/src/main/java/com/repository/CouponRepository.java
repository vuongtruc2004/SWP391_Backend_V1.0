package com.repository;


import com.entity.CouponEntity;
import com.repository.custom.JpaSpecificationRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;


public interface CouponRepository extends JpaSpecificationRepository<CouponEntity, Long> {
    Boolean existsByCouponCode(String code);

    @Query("SELECT c FROM CouponEntity c " +
            "WHERE c.endTime >= :now " +
            "AND (c.maxUses IS NULL OR c.maxUses > c.usedCount) " +
            "AND c.couponCode LIKE CONCAT('%', :searchCode, '%')")
    List<CouponEntity> getAllCouponsAvailable(@Param("now") Instant now, @Param("searchCode") String searchCode);

    List<CouponEntity> findByEndTimeBefore(Instant instant);

    Object existsByCouponCodeAndCouponIdNot(String couponCode, Long couponId);
}
