package com.repository;

import com.entity.CommentEntity;
import com.repository.custom.JpaSpecificationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaSpecificationRepository<CommentEntity, Long> {
    List<CommentEntity> findByParentComment_CommentId(Long parentCommentId);
    CommentEntity findByCommentId(Long commentId);

    Page<CommentEntity> findAllByParentCommentIsNull(Specification<CommentEntity> specification, Pageable pageable);
}
