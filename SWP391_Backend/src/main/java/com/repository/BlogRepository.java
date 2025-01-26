package com.repository;

import com.entity.BlogEntity;
import com.repository.custom.JpaSpecificationRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;

public interface BlogRepository extends JpaSpecificationRepository<BlogEntity, Long> {
    boolean existsBy();

    Optional<BlogEntity> findByPinned(boolean pinned);

    @Query("SELECT b.blogId FROM BlogEntity b " +
            "JOIN LikeEntity l ON b = l.blog " +
            "JOIN l.user u " +
            "WHERE u.userId = :userId")
    Set<Long> findAllLikeBlogs(@Param("userId") Long userId);

}
