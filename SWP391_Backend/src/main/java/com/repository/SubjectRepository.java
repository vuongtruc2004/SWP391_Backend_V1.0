package com.repository;

import com.entity.SubjectEntity;
import com.repository.custom.JpaSpecificationRepository;

public interface SubjectRepository extends JpaSpecificationRepository<SubjectEntity, Long> {
    boolean existsBy();
}
