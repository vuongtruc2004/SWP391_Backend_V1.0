package com.controller.api.v1;

import com.dto.request.LikeRequest;
import com.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/likes")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PostMapping
    public ResponseEntity<Void> addLike(@RequestBody LikeRequest likeRequest) {
        likeService.likeABlogOrComment(likeRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/dislike-blog/{blogId}")
    public ResponseEntity<Void> removeLike(@PathVariable("blogId") Long blogId) {
        likeService.dislikeABlog(blogId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/dislike-comment/{commentId}")
    public ResponseEntity<Void> removeLikeComment(@PathVariable(name = "commentId") Long commentId) {
        likeService.dislikeAComment(commentId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/check-like/{blogId}")
    public ResponseEntity<Boolean> checkLikeBlog(@PathVariable("blogId") Long blogId) {
        return ResponseEntity.ok(likeService.isLiked(blogId));
    }

    @GetMapping("/check-like-comment/{commentId}")
    public ResponseEntity<Boolean> checkLikeComment(@PathVariable("commentId") Long commentId) {
        return ResponseEntity.ok(likeService.isLikedComment(commentId));
    }

}
