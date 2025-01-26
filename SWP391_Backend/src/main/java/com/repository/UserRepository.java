package com.repository;

import com.entity.UserEntity;
import com.repository.custom.JpaSpecificationRepository;
import com.util.enums.AccountTypeEnum;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaSpecificationRepository<UserEntity, Long> {
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    UserEntity findByEmail(String email);

    Optional<UserEntity> findByUsername(String username);

    boolean existsBy();

    boolean existsByEmailOrUsername(String email, String username);

    Optional<UserEntity> findByUsernameAndAccountType(String username, AccountTypeEnum accountType);
}
