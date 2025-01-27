package com.service;

import com.dto.response.BlogResponse;
import com.dto.response.PageDetailsResponse;
import com.entity.BlogEntity;
import com.entity.UserEntity;
import com.exception.custom.NotFoundException;
import com.exception.custom.UserException;
import com.repository.BlogRepository;
import com.repository.UserRepository;
import com.service.auth.JwtService;
import com.util.BuildResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class BlogService {

    private final BlogRepository blogRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @Autowired
    public BlogService(BlogRepository blogRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.blogRepository = blogRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    public PageDetailsResponse<List<BlogResponse>> getBlogsWithFilter(
            Specification<BlogEntity> specification,
            Pageable pageable,
            String category
    ) {
        Set<Long> ids;
        if (category != null && !category.equals("all")) {
            String username = JwtService.extractUsernameFromToken()
                    .orElseThrow(() -> new UserException("Vui lòng đăng nhập để xem các bài viết đã thích/bình luận!"));
            UserEntity userEntity = userRepository.findByUsername(username)
                    .orElseThrow(() -> new UserException("Tên tài khoản không tồn tại!"));

            if (category.equals("like")) {
                ids = blogRepository.findAllLikeBlogs(userEntity.getUserId());
            } else {
                ids = blogRepository.findAllCommentBlogs(userEntity.getUserId());
            }
        } else {
            ids = new HashSet<>();
        }

        if (!ids.isEmpty()) {
            specification = Specification.where(specification)
                    .and((root, query, criteriaBuilder) -> root.get("blogId").in(ids));
        } else if (category != null && !category.equals("all")) {
            specification = Specification.where((root, query, criteriaBuilder) -> criteriaBuilder.disjunction());
        }

        Page<BlogEntity> page = blogRepository.findAll(specification, pageable);
        List<BlogResponse> blogResponseList = page.getContent().stream()
                .map(blogEntity -> modelMapper.map(blogEntity, BlogResponse.class))
                .toList();

        return BuildResponse.buildPageDetailsResponse(
                page.getNumber() + 1,
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements(),
                blogResponseList
        );
    }

    public BlogResponse getPinnedBlog() throws NotFoundException {
        BlogEntity blogEntity = blogRepository.findByPinned(true)
                .orElseThrow(() -> new NotFoundException("Không có bài viết nào được ghim!"));
        return modelMapper.map(blogEntity, BlogResponse.class);
    }
}
