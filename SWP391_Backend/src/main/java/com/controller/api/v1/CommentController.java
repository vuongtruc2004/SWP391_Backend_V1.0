package com.controller.api.v1;

import com.dto.request.CommentRequest;
import com.dto.response.CommentResponse;
import com.dto.response.PageDetailsResponse;
import com.entity.CommentEntity;
import com.service.CommentService;
import com.turkraft.springfilter.boot.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
public class CommentController {
    
    private final CommentService commentService;

    @GetMapping
    public ResponseEntity<PageDetailsResponse<List<CommentResponse>>> getAllCommentsOfBlogWithFilter(
            @Filter Specification<CommentEntity> specification,
            Pageable pageable
    ) {
        return ResponseEntity.ok(commentService.getAllCommentsOfBlogWithFilter(specification, pageable));
    }

    @PostMapping("/create-comment")
    public ResponseEntity<CommentResponse> commentABlog(@RequestBody CommentRequest commentRequest) {
        return ResponseEntity.ok(commentService.createCommentABlog(commentRequest));
    }

    @GetMapping("/child-comment/{parentCommentId}")
    public ResponseEntity<List<CommentResponse>> getChildComments(@PathVariable("parentCommentId") Long parentCommentId) {
        return ResponseEntity.ok(commentService.getCommentsByParentCommentId(parentCommentId));
    }

    @DeleteMapping("/delete-comment/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable("commentId") Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get-comment/{commentId}")
    public ResponseEntity<CommentResponse> getComment(@PathVariable("commentId") Long commentId) {
        return ResponseEntity.ok(commentService.getComment(commentId));
    }
}
