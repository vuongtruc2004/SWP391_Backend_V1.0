package com.repository;

import com.entity.UserEntity;
import com.repository.custom.JpaSpecificationRepository;
import com.util.enums.AccountTypeEnum;
import com.util.enums.RoleNameEnum;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaSpecificationRepository<UserEntity, Long> {
    boolean existsByEmailAndAccountType(String email, AccountTypeEnum accountType);

    boolean existsByEmailAndAccountTypeAndUserIdNot(String email, AccountTypeEnum accountType, Long userId);

    Optional<UserEntity> findByEmailAndAccountType(String email, AccountTypeEnum accountType);

    List<UserEntity> findAllByRole_RoleName(RoleNameEnum roleNameEnum);

    UserEntity existsByEmail(String email);
}
