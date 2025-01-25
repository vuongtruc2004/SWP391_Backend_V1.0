package com.repository;

import com.entity.BlogEntity;
import com.repository.custom.JpaSpecificationRepository;

public interface BlogRepository extends JpaSpecificationRepository<BlogEntity, Long> {
}
