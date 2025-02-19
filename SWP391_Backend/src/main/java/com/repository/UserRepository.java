package com.repository;

import com.entity.CourseEntity;
import com.entity.UserEntity;
import com.repository.custom.JpaSpecificationRepository;
import com.util.enums.AccountTypeEnum;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaSpecificationRepository<UserEntity, Long> {
    boolean existsByEmailAndAccountType(String email, AccountTypeEnum accountType);

    Optional<UserEntity> findByEmailAndAccountType(String email, AccountTypeEnum accountType);

    boolean existsByEmailAndAccountTypeAndUserIdNot(String email, AccountTypeEnum accountTypeEnum, Long userId);

    @Query("select " +
            "od.course " +
            "from UserEntity u " +
            "join u.orders o " +
            "join o.orderDetails od " +
            "where u.userId = :userId and o.orderStatus = 'COMPLETED'")
    Set<CourseEntity> getUserPurchaseCourses(@Param("userId") Long userId);

    Optional<UserEntity> findByUserIdAndLockedFalse(Long userId);
}
