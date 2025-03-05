package com.repository;

import com.entity.ChapterEntity;
import com.repository.custom.JpaSpecificationRepository;

import java.util.List;

public interface ChapterRepository extends JpaSpecificationRepository<ChapterEntity, Long> {
    List<ChapterEntity> findByCourse_CourseId(Long courseId);
}
