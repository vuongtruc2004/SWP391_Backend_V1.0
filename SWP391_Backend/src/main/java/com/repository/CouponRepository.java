package com.repository;


import com.entity.CouponEntity;
import com.repository.custom.JpaSpecificationRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;


public interface CouponRepository extends JpaSpecificationRepository<CouponEntity, Long> {
    Boolean existsByCouponCode(String code);

    List<CouponEntity> findByEndTimeBefore(Instant instant);
    List<CouponEntity> findByEndTimeAfter(Instant instant);
//    @Query("select distinct c from CouponEntity c " +
//            "left join c.courses crs " +
//            "where (crs.courseId in(:courseIds) or c.discountRange = 'ALL') " +
//            "and c.endTime >= :now and c.startTime <= :now " +
//            "and (c.maxUses is null or c.maxUses > c.usedCount)")
//    List<CouponEntity> findAllCouponsAvailableInACourses(@Param("courseIds") List<Long> courseIds, @Param("now") Instant now);
}
