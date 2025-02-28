package com.repository;

import com.entity.SubjectEntity;
import com.repository.custom.JpaSpecificationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;

public interface SubjectRepository extends JpaSpecificationRepository<SubjectEntity, Long> {
    @Query("SELECT s FROM SubjectEntity s " +
            "ORDER BY SIZE(s.courses) DESC")
    Page<SubjectEntity> findAllOrderByNumberOfCourses(Pageable pageable);

    boolean existsBySubjectName(String subjectName);

    SubjectEntity findBySubjectName(String subjectName);

    boolean existsBySubjectNameAndSubjectIdNot(String subjectName, Long subjectId);

    @Query("SELECT COUNT(s) > 0 FROM SubjectEntity s WHERE s.updatedAt IS NOT NULL")
    boolean hasUpdatedRecords();
}
