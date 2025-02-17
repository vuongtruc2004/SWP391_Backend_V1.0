package com.controller.api.v1;

import com.dto.response.CommentResponse;
import com.dto.response.PageDetailsResponse;
import com.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/{blogId}")
    public ResponseEntity<PageDetailsResponse<List<CommentResponse>>> getAllCommentOfBlogs(@PathVariable("blogId") Long blogId, Pageable pageable){
        return ResponseEntity.ok(commentService.getAllCommentsOfBlog(blogId, pageable));
    }

    @GetMapping("/reply/{parent-comment-id}")
    public ResponseEntity<List<CommentResponse>> getAllReply(@PathVariable("parent-comment-id") Long parentCommentId){
        return ResponseEntity.ok(commentService.getAllReplyComments(parentCommentId));
    }
}
