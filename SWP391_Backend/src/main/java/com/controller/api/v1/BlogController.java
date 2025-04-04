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
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/blogs")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;

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
        return ResponseEntity.ok(blogService.getBlogWithFilterPageAdmin(specification, pageable));
    }

    @PatchMapping("/update/{blogId}")
    public ResponseEntity<ApiResponse<BlogResponse>> updateBlog(@PathVariable("blogId") Long blogId, @RequestBody BlogRequest blogRequest) {
        return ResponseEntity.ok(blogService.updateBlog(blogId, blogRequest));
    }

    @PatchMapping("/update-draft/{blogId}")
    public ResponseEntity<ApiResponse<BlogResponse>> updateDraftBlog(@PathVariable("blogId") Long blogId, @RequestBody BlogRequest blogRequest) {
        return ResponseEntity.ok(blogService.updateSaveDraft(blogId, blogRequest));
    }

    @PostMapping("/create-blog")
    public ResponseEntity<ApiResponse<BlogResponse>> createBlog(@RequestBody BlogRequest blogRequest) {
        return ResponseEntity.ok(blogService.createBlog(blogRequest));
    }

    @PostMapping("/save-draft")
    public ResponseEntity<ApiResponse<BlogResponse>> saveDraft(@RequestBody BlogRequest blogRequest) {
        return ResponseEntity.ok(blogService.saveDraft(blogRequest));
    }

    @PostMapping("/up-thumbnail")
    public ResponseEntity<ApiResponse<String>> upThumbnail(@RequestParam("file") MultipartFile file, @RequestParam("folder") String folder) throws URISyntaxException, IOException {
        return ResponseEntity.ok(blogService.getThumbnail(file, folder));
    }

    @PutMapping("/status/{blogId}")
    public ResponseEntity<ApiResponse<BlogResponse>> changeStatus(@PathVariable("blogId") Long blogId) {
        return ResponseEntity.ok((blogService.changePublishedOfBlog(blogId)));
    }

    @DeleteMapping("/delete/{blogId}")
    public ResponseEntity<ApiResponse<String>> deleteBlog(@PathVariable("blogId") Long blogId) {
        return ResponseEntity.ok(blogService.deleteBlog(blogId));
    }
}
