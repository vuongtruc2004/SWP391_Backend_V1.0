package com.repository;

import com.entity.ExpertEntity;
import com.repository.custom.JpaSpecificationRepository;
import com.util.enums.AccountTypeEnum;

import java.util.Optional;

public interface ExpertRepository extends JpaSpecificationRepository<ExpertEntity, Long> {
    Optional<ExpertEntity> findByUser_EmailAndUser_AccountType(String userEmail, AccountTypeEnum userAccountType);
}
