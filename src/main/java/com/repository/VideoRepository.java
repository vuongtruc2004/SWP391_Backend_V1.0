package com.repository;

import com.entity.VideoEntity;
import com.repository.custom.JpaSpecificationRepository;

public interface VideoRepository extends JpaSpecificationRepository<VideoEntity, Long> {
}
