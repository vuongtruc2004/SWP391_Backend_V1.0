package com.repository;

import com.entity.LessonEntity;
import com.repository.custom.JpaSpecificationRepository;

public interface LessonRepository extends JpaSpecificationRepository<LessonEntity, Long> {
}
