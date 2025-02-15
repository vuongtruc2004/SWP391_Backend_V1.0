package com.repository;

import com.entity.AnswerEntity;
import com.repository.custom.JpaSpecificationRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaSpecificationRepository<AnswerEntity, Long> {
}
