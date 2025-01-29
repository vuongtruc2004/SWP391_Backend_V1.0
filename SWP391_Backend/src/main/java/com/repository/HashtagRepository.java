package com.repository;

import com.entity.HashtagEntity;
import com.repository.custom.JpaSpecificationRepository;

public interface HashtagRepository extends JpaSpecificationRepository<HashtagEntity, Long> {
    boolean existsBy();
}
