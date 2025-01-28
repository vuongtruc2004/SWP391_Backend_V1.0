package com.controller.api.v1;

import com.dto.response.BlogResponse;
import com.dto.response.PageDetailsResponse;
import com.entity.BlogEntity;
import com.service.BlogService;
import com.turkraft.springfilter.boot.Filter;
import com.util.annotation.ApiMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/blogs")
public class BlogController {

    private final BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @ApiMessage("Lấy các bài viết thành công!")
    @GetMapping
    public ResponseEntity<PageDetailsResponse<List<BlogResponse>>> getBlogsWithFilter(
            @Filter Specification<BlogEntity> specification,
            Pageable pageable,
            @RequestParam(required = false, name = "category", defaultValue = "all") String category
    ) {
        return ResponseEntity.ok(blogService.getBlogsWithFilter(specification, pageable, category));
    }

    @ApiMessage("Lấy các bài viết của tác giả thành công!")
    @GetMapping("/author")
    public ResponseEntity<PageDetailsResponse<List<BlogResponse>>> getBlogsOfSameAuthor(
            @RequestParam("blogId") Long blogId,
            Pageable pageable
    ) {
        return ResponseEntity.ok(blogService.getBlogsOfSameAuthor(blogId, pageable));
    }

    @ApiMessage("Lấy bài viết thành công!")
    @GetMapping("{id}")
    public ResponseEntity<BlogResponse> getBlogById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(blogService.getBlogById(id));
    }

    @ApiMessage("Lấy bài viết đã ghim thành công!")
    @GetMapping("/pinned")
    public ResponseEntity<BlogResponse> getPinnedBlog() {
        return ResponseEntity.ok(blogService.getPinnedBlog());
    }
}
