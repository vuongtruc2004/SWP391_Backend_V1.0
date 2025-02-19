package com.repository;

import com.entity.CourseEntity;
import com.entity.ExpertEntity;
import com.entity.UserEntity;
import com.repository.custom.JpaSpecificationRepository;
import com.util.enums.AccountTypeEnum;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ExpertRepository extends JpaSpecificationRepository<ExpertEntity, Long> {
    Optional<ExpertEntity> findByUser_EmailAndUser_AccountType(String userEmail, AccountTypeEnum userAccountType);

    ExpertEntity findByCourses(CourseEntity course);
    
    @Query("select " +
            "distinct o.user " +
            "from ExpertEntity e " +
            "join e.courses c " +
            "join c.orderDetails od " +
            "join od.order o " +
            "where e.expertId = :expertId")
    List<UserEntity> getAllStudents(@Param("expertId") Long expertId);
}
