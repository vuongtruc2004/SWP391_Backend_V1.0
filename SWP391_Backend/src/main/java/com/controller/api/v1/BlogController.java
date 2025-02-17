package com.controller.api.v1;

import com.dto.request.BlogRequest;
import com.dto.response.ApiResponse;
import com.dto.response.BlogResponse;
import com.dto.response.PageDetailsResponse;
import com.dto.response.details.BlogDetailsResponse;
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
            @RequestParam(name = "category", required = false, defaultValue = "all") String category,
            @RequestParam(name = "tag_name", required = false) List<String> tagNameList
    ) {
        return ResponseEntity.ok(blogService.getBlogsWithFilter(specification, pageable, category, tagNameList));
    }

    @ApiMessage("Lấy các bài viết cùng tác giả thành công!")
    @GetMapping("/author")
    public ResponseEntity<PageDetailsResponse<List<BlogResponse>>> getBlogsOfSameAuthor(
            @RequestParam("blogId") Long blogId,
            Pageable pageable
    ) {
        return ResponseEntity.ok(blogService.getBlogsOfSameAuthor(blogId, pageable));
    }

    @ApiMessage("Lấy bài viết thành công!")
    @GetMapping("/{id}")
    public ResponseEntity<BlogResponse> getBlogById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(blogService.getBlogById(id));
    }

    @ApiMessage("Lấy bài viết đã ghim thành công!")
    @GetMapping("/pinned")
    public ResponseEntity<BlogResponse> getPinnedBlog() {
        return ResponseEntity.ok(blogService.getPinnedBlog());
    }

    @GetMapping("/all")
    public ResponseEntity<PageDetailsResponse<List<BlogDetailsResponse>>> getAllBlogsPageAdmin(@Filter Specification<BlogEntity> specification, Pageable pageable) {
        return  ResponseEntity.ok(blogService.getBlogWithFilterPageAdmin(specification, pageable));
    }

    @PatchMapping("/update/{blogId}")
    public ResponseEntity<ApiResponse<BlogResponse>> updateBlog(@PathVariable("blogid") Long blogId, @RequestBody BlogRequest blogRequest) {
        return ResponseEntity.ok(blogService.updateBlog(blogId, blogRequest));
    }

    @PostMapping("/create-blog")
    public ResponseEntity<ApiResponse<BlogResponse>> createBlog(@RequestBody BlogRequest blogRequest) {
        return ResponseEntity.ok(blogService.createBlog(blogRequest));
    }
}
