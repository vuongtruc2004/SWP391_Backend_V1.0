package com.repository;

import com.entity.UserEntity;
import com.repository.custom.JpaSpecificationRepository;
import com.util.enums.AccountTypeEnum;
import com.util.enums.GenderEnum;
import com.util.enums.RoleNameEnum;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaSpecificationRepository<UserEntity, Long> {
    boolean existsByEmailAndAccountType(String email, AccountTypeEnum accountType);

    Optional<UserEntity> findByEmailAndAccountType(String email, AccountTypeEnum accountType);

    boolean existsByEmailAndAccountTypeAndUserIdNot(String email, AccountTypeEnum accountTypeEnum, Long userId);

    Optional<UserEntity> findByUserIdAndLockedFalse(Long userId);

    Long countByGender(GenderEnum gender);

    Long countByDobBetween(Instant startDate, Instant endDate);

    UserEntity findByEmail(String email);

    List<UserEntity> findAllByExpertsIsNotEmptyAndUserId(Long userId);

    Collection<Object> findAllByRole_RoleName(RoleNameEnum roleRoleName);
}
