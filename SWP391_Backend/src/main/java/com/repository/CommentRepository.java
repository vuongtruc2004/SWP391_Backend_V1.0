package com.repository;

import com.entity.BlogEntity;
import com.entity.CommentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    Page<CommentEntity> findByBlog(Pageable pageable, BlogEntity blog);

    @Query("select c from CommentEntity c where c.parentComment.commentId = :parentCommentId")
    List<CommentEntity> findAllByParentCommentId(Long parentCommentId);
}
