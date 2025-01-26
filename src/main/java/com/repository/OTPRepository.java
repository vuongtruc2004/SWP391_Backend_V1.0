package com.repository;

import com.entity.OTPEntity;
import com.repository.custom.JpaSpecificationRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OTPRepository extends JpaSpecificationRepository<OTPEntity, Long> {
    List<OTPEntity> findAllByActive(boolean active);

    OTPEntity findByToken(String token);
}