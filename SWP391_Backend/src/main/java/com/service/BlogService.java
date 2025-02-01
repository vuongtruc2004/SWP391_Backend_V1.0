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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
            String category,
            List<String> tagNameList
    ) {
        Set<Long> ids;
        if (category != null && !category.equals("all")) {
            String username = JwtService.extractUsernameFromToken()
                    .orElseThrow(() -> new UserException("Vui lòng đăng nhập để xem các bài viết đã thích/bình luận!"));
            UserEntity userEntity = userRepository.findByUsername(username)
                    .orElseThrow(() -> new NotFoundException("Tên tài khoản không tồn tại!"));

            if (category.equals("like")) {
                ids = blogRepository.findAllLikeBlogs(userEntity.getUserId());
            } else if (category.equals("comment")) {
                ids = blogRepository.findAllCommentBlogs(userEntity.getUserId());
            } else {
                ids = blogRepository.findAllByUser_UserId(userEntity.getUserId()).stream()
                        .map(BlogEntity::getBlogId).collect(Collectors.toSet());
            }
        } else {
            ids = new HashSet<>();
        }

        if (!category.equals("owner")) {
            specification = Specification.where(specification)
                    .and((root, query, criteriaBuilder) ->
                            criteriaBuilder.and(
                                    criteriaBuilder.isTrue(root.get("accepted")),
                                    criteriaBuilder.isTrue(root.get("published"))
                            )
                    );
        }

        if (!ids.isEmpty()) {
            specification = Specification.where(specification)
                    .and((root, query, criteriaBuilder) ->
                            root.get("blogId").in(ids)
                    );
        } else if (category != null && !category.equals("all")) {
            specification = Specification.where((root, query, criteriaBuilder) ->
                    criteriaBuilder.disjunction()
            );
        }

        if (tagNameList != null && !tagNameList.isEmpty()) {
            specification = Specification.where(specification)
                    .and(((root, query, criteriaBuilder) ->
                            root.join("hashtags").get("tagName").in(tagNameList)
                    ));
        }

        Page<BlogEntity> page = blogRepository.findAll(specification, pageable);
        return getListPageDetailsResponse(page, null);
    }

    public PageDetailsResponse<List<BlogResponse>> getBlogsOfSameAuthor(Long blogId, Pageable pageable) {
        BlogEntity blogEntity = blogRepository.findByBlogIdAndPublishedTrueAndAcceptedTrue(blogId)
                .orElseThrow(() -> new NotFoundException("Không có bài viết nào với ID = " + blogId));
        UserEntity userEntity = blogEntity.getUser();
        Page<BlogEntity> page = blogRepository.findAllByUser_UserIdAndBlogIdNotAndPublishedTrueAndAcceptedTrue(userEntity.getUserId(), blogId, pageable);
        return getListPageDetailsResponse(page, blogEntity);
    }

    public BlogResponse getBlogById(Long blogId) {
        String username = JwtService.extractUsernameFromToken().orElse(null);
        BlogEntity blogEntity = blogRepository.findById(blogId)
                .orElseThrow(() -> new NotFoundException("Không có bài viết nào với ID = " + blogId));

        if (username == null) {
            if (!blogEntity.getPublished() || !blogEntity.getAccepted()) {
                throw new NotFoundException("Không có bài viết nào với ID = " + blogId);
            }
        } else {
            UserEntity user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new NotFoundException("Tên tài khoản không tồn tại!"));
            if ((!blogEntity.getPublished() || !blogEntity.getAccepted())
                    && !blogEntity.getUser().equals(user)) {
                throw new NotFoundException("Không có bài viết nào với ID = " + blogId);
            }
        }
        return this.convertToBlogResponse(blogEntity);
    }

    public BlogResponse getPinnedBlog() {
        BlogEntity blogEntity = blogRepository.findByPinnedTrueAndPublishedTrueAndAcceptedTrue()
                .orElseThrow(() -> new NotFoundException("Không có bài viết nào được ghim!"));
        return this.convertToBlogResponse(blogEntity);
    }

    private PageDetailsResponse<List<BlogResponse>> getListPageDetailsResponse(Page<BlogEntity> page, BlogEntity currentBlogEntity) {
        List<BlogResponse> blogResponseList = new ArrayList<>(page.getContent().stream()
                .map(blogEntity -> {
                    BlogResponse blogResponse = modelMapper.map(blogEntity, BlogResponse.class);
                    blogResponse.setTotalLikes(blogEntity.getLikes().size());
                    blogResponse.setTotalComments(blogEntity.getComments().size());
                    return blogResponse;
                })
                .toList());

        if (currentBlogEntity != null) {
            blogResponseList.add(modelMapper.map(currentBlogEntity, BlogResponse.class));
        }

        return BuildResponse.buildPageDetailsResponse(
                page.getNumber() + 1,
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements(),
                blogResponseList
        );
    }

    private BlogResponse convertToBlogResponse(BlogEntity blogEntity) {
        BlogResponse blogResponse = modelMapper.map(blogEntity, BlogResponse.class);
        blogResponse.setTotalLikes(blogEntity.getLikes().size());
        blogResponse.setTotalComments(blogEntity.getComments().size());
        return blogResponse;
    }
}
