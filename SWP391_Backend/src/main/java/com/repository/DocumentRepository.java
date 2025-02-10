package com.repository;

import com.entity.DocumentEntity;
import com.repository.custom.JpaSpecificationRepository;

public interface DocumentRepository extends JpaSpecificationRepository<DocumentEntity, Long> {
}
