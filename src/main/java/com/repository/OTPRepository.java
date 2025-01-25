package com.repository;

import com.entity.OTPEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OTPRepository extends JpaRepository<OTPEntity, Long> {
    List<OTPEntity> findAllByActive(boolean active);
    OTPEntity findByToken(String token);
}