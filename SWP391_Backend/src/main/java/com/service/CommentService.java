package com.service;

import com.dto.response.ApiResponse;
import com.dto.response.CommentResponse;
import com.dto.response.PageDetailsResponse;
import com.entity.BlogEntity;
import com.entity.CommentEntity;
import com.exception.custom.NotFoundException;
import com.repository.BlogRepository;
import com.repository.CommentRepository;
import com.util.BuildResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;
    private final BlogRepository blogRepository;

    public PageDetailsResponse<List<CommentResponse>> getAllCommentsOfBlog(Long blogId, Pageable pageable) {
        BlogEntity blogEntity = blogRepository.findById(blogId).orElseThrow(()-> new NotFoundException("Không tìm thấy bài viết!"));
        Page<CommentEntity> page = commentRepository.findByBlog(pageable, blogEntity);
        List<CommentResponse> listComment = page.getContent().stream().map(commentEntity -> {
            CommentResponse commentResponse = modelMapper.map(commentEntity, CommentResponse.class);
            return commentResponse;
        }).toList();
        return BuildResponse.buildPageDetailsResponse(
                page.getNumber()+1,
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements(),
                listComment
        );
    }

    public List<CommentResponse> getAllReplyComments(Long parentCommentId) {
        List<CommentResponse> commentEntityList = commentRepository.findAllByParentCommentId(parentCommentId).stream()
                .map(commentEntity -> {
                    CommentResponse commentResponse = modelMapper.map(commentEntity, CommentResponse.class);
                    return commentResponse;
                }).toList();
        return commentEntityList;
    }
}
