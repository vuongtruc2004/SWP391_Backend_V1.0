package com.repository;

import com.entity.DocumentEntity;
import com.repository.custom.JpaSpecificationRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaSpecificationRepository<DocumentEntity, Long> {
    boolean existsByTitle(String title);
}
