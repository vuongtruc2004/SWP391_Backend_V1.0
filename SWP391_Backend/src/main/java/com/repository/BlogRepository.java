package com.repository;

import com.entity.BlogEntity;
import com.repository.custom.JpaSpecificationRepository;
import com.util.enums.BlogStatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;

public interface BlogRepository extends JpaSpecificationRepository<BlogEntity, Long> {
    Optional<BlogEntity> findByPinnedTrueAndBlogStatus(BlogStatusEnum blogStatus);

    @Query("SELECT b.blogId FROM BlogEntity b " +
            "JOIN LikeEntity l ON b = l.blog " +
            "JOIN l.user u " +
            "WHERE u.userId = :userId")
    Set<Long> findAllLikeBlogs(@Param("userId") Long userId);

    @Query("SELECT b.blogId FROM BlogEntity b " +
            "JOIN CommentEntity c ON b = c.blog " +
            "JOIN c.user u " +
            "WHERE u.userId = :userId")
    Set<Long> findAllCommentBlogs(@Param("userId") Long userId);

    Set<BlogEntity> findAllByUser_UserId(Long userId);

    Page<BlogEntity> findAllByUser_UserIdAndBlogIdAndBlogStatus(Long userId, Long blogId, Pageable pageable, BlogStatusEnum blogStatus);

    Optional<BlogEntity> findByBlogIdAndBlogStatus(Long blogId, BlogStatusEnum blogStatus);
}
