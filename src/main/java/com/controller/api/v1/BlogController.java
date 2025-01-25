package com.controller.api.v1;

import com.dto.response.ApiResponse;
import com.dto.response.BlogResponse;
import com.dto.response.PageDetailsResponse;
import com.entity.BlogEntity;
import com.service.BlogService;
import com.turkraft.springfilter.boot.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/blogs")
public class BlogController {

    public BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping()
    public ResponseEntity<ApiResponse<PageDetailsResponse<List<BlogResponse>>>> getAllBlogsWithFilter(
            @Filter Specification<BlogEntity> specification,
            Pageable pageable
            ) {
        return ResponseEntity.ok(blogService.getBlogsWithFilter(specification, pageable));
    }
}
