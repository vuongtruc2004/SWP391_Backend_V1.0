package com.service;

import com.dto.response.ApiResponse;
import com.dto.response.BlogResponse;
import com.dto.response.PageDetailsResponse;
import com.entity.BlogEntity;
import com.repository.BlogRepository;
import com.util.BuildResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {

    private final BlogRepository blogRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public BlogService(BlogRepository blogRepository, ModelMapper modelMapper) {
        this.blogRepository = blogRepository;
        this.modelMapper = modelMapper;
    }

    public ApiResponse<PageDetailsResponse<List<BlogResponse>>> getBlogsWithFilter(
            Specification<BlogEntity> specification,
            Pageable pageable
    ) {
        Page<BlogEntity> page = blogRepository.findAll(specification, pageable);
        List<BlogResponse> blogResponseList = page.getContent().stream()
                .map(blogEntity -> modelMapper.map(blogEntity, BlogResponse.class))
                .toList();

        PageDetailsResponse<List<BlogResponse>> pageDetailsResponse = BuildResponse.buildPageDetailsResponse(
                page.getNumber() + 1,
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements(),
                blogResponseList
        );
        return BuildResponse.buildApiResponse(200, "Lấy các bài viết thành công!", pageDetailsResponse);
    }
}
