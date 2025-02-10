package com.repository;

import com.entity.RoleEntity;
import com.repository.custom.JpaSpecificationRepository;
import com.util.enums.RoleNameEnum;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaSpecificationRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByRoleName(RoleNameEnum roleName);
}
