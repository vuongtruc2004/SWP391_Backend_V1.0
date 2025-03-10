package com.repository;

import com.entity.ChapterEntity;
import com.repository.custom.JpaSpecificationRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ChapterRepository extends JpaSpecificationRepository<ChapterEntity, Long> {
    @Query("select c from ChapterEntity c " +
            "join c.course crs " +
            "where crs.expert.expertId = :expertId and c.chapterId = :chapterId")
    Optional<ChapterEntity> findByExpertIdAndChapterId(@Param("expertId") Long expertId, @Param("chapterId") Long chapterId);
}
