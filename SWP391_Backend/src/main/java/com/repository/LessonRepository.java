package com.repository;

import com.entity.LessonEntity;
import com.repository.custom.JpaSpecificationRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepository extends JpaSpecificationRepository<LessonEntity, Long> {
    Boolean existsByTitle(String title);
}
