package com.repository.custom;

import com.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>, JpaSpecificationExecutor<UserEntity> {
    UserEntity findByEmail(String email);
    UserEntity findByUsername(String username);
    boolean existsByEmailOrUsername(String email,String username);
}
