package com.repository;

import com.entity.OTPEntity;
import com.repository.custom.JpaSpecificationRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Optional;
import java.util.Set;

@Repository
public interface OTPRepository extends JpaSpecificationRepository<OTPEntity, Long> {
    Optional<OTPEntity> findByCode(String code);

    @Query("SELECT o FROM OTPEntity o WHERE o.expiredAt < :currentTime")
    Set<OTPEntity> findAllExpiredOTP(@Param("currentTime") Instant currentTime);
}