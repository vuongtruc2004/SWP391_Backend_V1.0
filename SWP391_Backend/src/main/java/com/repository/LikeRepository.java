package com.repository;

import com.entity.LikeEntity;
import com.repository.custom.JpaSpecificationRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaSpecificationRepository<LikeEntity, Long> {

    LikeEntity findByUser_UserIdAndBlog_BlogId(Long userId, Long blogId);

    LikeEntity findByUser_UserIdAndComment_CommentId(Long userId, Long commentId);

    Boolean existsByUser_UserIdAndBlog_BlogId(Long userId, Long blogId);

    Boolean existsByUser_UserIdAndComment_CommentId(Long userId, Long commentId);

    void deleteByComment_CommentId(Long commentId);

    List<LikeEntity> findAllByBlog_BlogIdAndUser_UserId(Long blogId, Long userId);
}
