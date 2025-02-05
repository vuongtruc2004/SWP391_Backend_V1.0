package com.repository;

import com.entity.CourseEntity;
import com.entity.SubjectEntity;
import com.entity.UserEntity;
import com.repository.custom.JpaSpecificationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

public interface SubjectRepository extends JpaSpecificationRepository<SubjectEntity, Long> {
    @Query("SELECT s FROM SubjectEntity s " +
            "ORDER BY SIZE(s.courses) DESC")
    Page<SubjectEntity> findAllOrderByNumberOfCourses(Pageable pageable);


}
