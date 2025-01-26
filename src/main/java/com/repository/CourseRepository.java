package com.repository;

import com.entity.CourseEntity;
import com.entity.UserEntity;
import com.repository.custom.JpaSpecificationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;



@Repository
public interface CourseRepository extends JpaSpecificationRepository<CourseEntity, Long> {
    Page<CourseEntity> findAllByPurchasers(UserEntity user, Pageable pageable);
}
