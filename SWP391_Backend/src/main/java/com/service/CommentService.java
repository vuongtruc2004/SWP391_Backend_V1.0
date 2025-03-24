package com.service;

import com.dto.request.CommentRequest;
import com.dto.response.CommentResponse;
import com.dto.response.PageDetailsResponse;
import com.entity.BlogEntity;
import com.entity.CommentEntity;
import com.entity.UserEntity;
import com.exception.custom.NotFoundException;
import com.helper.UserServiceHelper;
import com.repository.BlogRepository;
import com.repository.CommentRepository;
import com.repository.LikeRepository;
import com.util.BuildResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserServiceHelper userServiceHelper;
    private final BlogRepository blogRepository;
    private final ModelMapper modelMapper;
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final LikeRepository likeRepository;

    //Comment a blog or reply a comment
    public CommentResponse createCommentABlog(CommentRequest commentRequest) {
        UserEntity author = userServiceHelper.extractUserFromToken();
        if(author == null) {
            throw new NotFoundException("Không tìm thấy người dùng hợp lệ!");
        }

        CommentEntity commentEntity = new CommentEntity();

        BlogEntity blog = blogRepository.findById(commentRequest.getBlog()).orElseThrow(()->new NotFoundException("Không tìm thấy bài viết hợp lệ!"));
        commentEntity.setUser(author);
        commentEntity.setContent(commentRequest.getContent());
        commentEntity.setBlog(blog);
        if(commentRequest.getParentComment() != null){
            commentEntity.setParentComment(commentRepository.findByCommentId(commentRequest.getParentComment()));
        } else {
            commentEntity.setParentComment(null);
        }

        commentRepository.save(commentEntity);
        CommentResponse commentResponse = modelMapper.map(commentEntity, CommentResponse.class);
        simpMessagingTemplate.convertAndSend("/topic/comments/"+commentRequest.getBlog(), commentResponse);
        return commentResponse;
    }

    public PageDetailsResponse<List<CommentResponse>> getAllCommentsOfBlogWithFilter(
            Specification<CommentEntity> specification,
            Pageable pageable
    ){
        Page<CommentEntity> page = commentRepository.findAll(specification, pageable);
        List<CommentResponse> commentResponseList = page.stream().map(commentEntity -> modelMapper.map(commentEntity, CommentResponse.class)).toList();
//        createCommentSuccess();
        return BuildResponse.buildPageDetailsResponse(
                page.getNumber() + 1,
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements(),
                commentResponseList
        );
    }

    //Get children comment of a comment
    public List<CommentResponse> getCommentsByParentCommentId(Long parentCommentId) {
        return commentRepository.findByParentComment_CommentId(parentCommentId).stream()
                .map(commentEntity -> modelMapper.map(commentEntity, CommentResponse.class)).toList();

    }

    //delete a comment
    @Transactional
    public void deleteComment(Long commentId) {
        CommentEntity commentEntity = commentRepository.findByCommentId(commentId);
        likeRepository.deleteByComment_CommentId(commentId);
        commentRepository.delete(commentEntity);
    }

    public CommentResponse getComment(Long commentId) {
        CommentEntity commentEntity = commentRepository.findByCommentId(commentId);
        return modelMapper.map(commentEntity, CommentResponse.class);
    }
}
