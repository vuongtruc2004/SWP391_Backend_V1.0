package com.service;

import com.dto.request.BlogRequest;
import com.dto.response.ApiResponse;
import com.dto.response.BlogResponse;
import com.dto.response.PageDetailsResponse;
import com.dto.response.UserResponse;
import com.dto.response.details.BlogDetailsResponse;
import com.entity.BlogEntity;
import com.entity.UserEntity;
import com.exception.custom.NotFoundException;
import com.exception.custom.StorageException;
import com.exception.custom.UserException;
import com.helper.BlogServiceHelper;
import com.helper.UserServiceHelper;
import com.repository.BlogRepository;
import com.repository.UserRepository;
import com.service.auth.JwtService;
import com.util.BuildResponse;
import com.util.enums.AccountTypeEnum;
import com.util.enums.BlogStatusEnum;
import com.util.enums.RoleNameEnum;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final FileService fileService;
    private final UserServiceHelper userServiceHelper;
    private final HashtagService hashtagService;
    private final BlogServiceHelper blogServiceHelper;

    public PageDetailsResponse<List<BlogResponse>> getBlogsWithFilter(
            Specification<BlogEntity> specification,
            Pageable pageable,
            String category,
            List<String> tagNameList
    ) {
        Set<Long> ids;
        if (category != null && !category.equals("all")) {
            String email = JwtService.extractUsernameFromToken()
                    .orElseThrow(() -> new UserException("Vui lòng đăng nhập để xem các bài viết đã thích/bình luận!"));
            String accountType = JwtService.extractAccountTypeFromToken()
                    .orElseThrow(() -> new UserException("Vui lòng đăng nhập để xem các bài viết đã thích/bình luận!"));

            UserEntity userEntity = userRepository.findByEmailAndAccountType(email, AccountTypeEnum.valueOf(accountType))
                    .orElseThrow(() -> new NotFoundException("Tài khoản không tồn tại!"));

            if (category.equals("like")) {
                ids = blogRepository.findAllLikeBlogs(userEntity.getUserId());
            } else {
                ids = blogRepository.findAllCommentBlogs(userEntity.getUserId());
            }
        } else {
            ids = new HashSet<>();
        }

        specification = Specification.where(specification)
                .and(((root, query, criteriaBuilder) ->
                        criteriaBuilder.equal(root.get("blogStatus"), BlogStatusEnum.PUBLISH)));
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
        return blogServiceHelper.getListPageDetailsResponse(page, null);
    }

    public PageDetailsResponse<List<BlogResponse>> getBlogsOfSameAuthor(Long blogId, Pageable pageable) {
        BlogEntity blogEntity = blogRepository.findByBlogIdAndBlogStatus(blogId, BlogStatusEnum.PUBLISH)
                .orElseThrow(() -> new NotFoundException("Không có bài viết nào với ID = " + blogId));
        UserEntity userEntity = blogEntity.getUser();
        Page<BlogEntity> page = blogRepository.findAllByUser_UserIdAndBlogIdAndBlogStatus(userEntity.getUserId(), blogId, pageable, BlogStatusEnum.PUBLISH);
        return blogServiceHelper.getListPageDetailsResponse(page, blogEntity);
    }

    public BlogResponse getBlogById(Long blogId) {
        BlogEntity blogEntity = blogRepository.findByBlogIdAndBlogStatus(blogId, BlogStatusEnum.PUBLISH)
                .orElseThrow(() -> new NotFoundException("Không có bài viết nào với ID = " + blogId));
        return blogServiceHelper.convertToBlogResponse(blogEntity);
    }

    public BlogResponse getPinnedBlog() {
        BlogEntity blogEntity = blogRepository.findByPinnedTrueAndBlogStatus(BlogStatusEnum.PUBLISH)
                .orElseThrow(() -> new NotFoundException("Không có bài viết nào được ghim!"));
        return blogServiceHelper.convertToBlogResponse(blogEntity);
    }

    public PageDetailsResponse<List<BlogDetailsResponse>> getBlogWithFilterPageAdmin(
            Specification<BlogEntity> specification,
            Pageable pageable
    ) {
        UserEntity userEntity = userServiceHelper.extractUserFromToken();
        if (userEntity == null) {
            throw new UserException("Bạn cần đăng nhập để thực hiện chức năng này!");
        }

        if (userEntity.getRole().getRoleName().equals(RoleNameEnum.ADMIN)) {
            specification = specification.and((root, query, criteriaBuilder) -> {
                Predicate predicate = criteriaBuilder.and(
                        criteriaBuilder.notEqual(root.get("user").get("userId"), userEntity.getUserId()),
                        criteriaBuilder.notEqual(root.get("blogStatus"), BlogStatusEnum.DRAFT)
                );

                Predicate ownBlog = criteriaBuilder.equal(root.get("user").get("userId"), userEntity.getUserId());
                return criteriaBuilder.or(predicate, ownBlog);
            });
        }

        if (userEntity.getRole().getRoleName().equals(RoleNameEnum.MARKETING) || userEntity.getRole().getRoleName().equals(RoleNameEnum.EXPERT)) {
            specification = specification.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("user").get("userId"), userEntity.getUserId()));
        }
        Page<BlogEntity> page = blogRepository.findAll(specification, pageable);
        List<BlogDetailsResponse> listResponse = page.getContent().stream().map(blogEntity -> {
            BlogDetailsResponse blogResponse = modelMapper.map(blogEntity, BlogDetailsResponse.class);
            blogResponse.setTotalLikes(blogEntity.getLikes().size());
            blogResponse.setTotalComments(blogEntity.getComments().size());
            blogResponse.setUser(modelMapper.map(blogEntity.getUser(), UserResponse.class));
            return blogResponse;
        }).toList();

        return BuildResponse.buildPageDetailsResponse(
                page.getNumber() + 1,
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements(),
                listResponse

        );
    }

    public ApiResponse<BlogResponse> updateBlog(Long blogId, BlogRequest blogRequest) {
        BlogEntity blogEntity = blogRepository.findById(blogId).orElseThrow(() -> new NotFoundException("Không tìm thấy bài viết!"));
        modelMapper.map(blogRequest, blogEntity);
        blogEntity.setBlogStatus(BlogStatusEnum.PUBLISH);
        blogEntity.setHashtags(hashtagService.saveAllHashtagsOfBlog(blogRequest));
        blogRepository.save(blogEntity);
        return BuildResponse.buildApiResponse(
                HttpStatus.OK.value(),
                "Thành Công!",
                "Bài viết đã được cập nhật!",
                modelMapper.map(blogEntity, BlogResponse.class)
        );
    }

    public ApiResponse<BlogResponse> updateSaveDraft(Long blogId, BlogRequest blogRequest) {
        BlogEntity blogEntity = blogRepository.findById(blogId).orElseThrow(() -> new NotFoundException("Không tìm thấy bài viết!"));
        modelMapper.map(blogRequest, blogEntity);
        blogEntity.setBlogStatus(BlogStatusEnum.DRAFT);
        blogEntity.setHashtags(hashtagService.saveAllHashtagsOfBlog(blogRequest));
        blogRepository.save(blogEntity);
        return BuildResponse.buildApiResponse(
                HttpStatus.OK.value(),
                "Thành Công!",
                "Bài viết đã được cập nhật!",
                modelMapper.map(blogEntity, BlogResponse.class)
        );
    }

    public ApiResponse<BlogResponse> createBlog(BlogRequest blogRequest) {
        UserEntity author = userServiceHelper.extractUserFromToken();
        BlogEntity blogEntity = modelMapper.map(blogRequest, BlogEntity.class);
        blogEntity.setUser(author);
        blogEntity.setBlogStatus(BlogStatusEnum.PUBLISH);
        blogEntity.setHashtags(hashtagService.saveAllHashtagsOfBlog(blogRequest));
        blogRepository.save(blogEntity);
        return BuildResponse.buildApiResponse(
                HttpStatus.CREATED.value(),
                "Thành Công!",
                "Bài viết đã được tạo!",
                modelMapper.map(blogEntity, BlogResponse.class)
        );
    }

    public ApiResponse<BlogResponse> saveDraft(BlogRequest blogRequest) {
        UserEntity author = userServiceHelper.extractUserFromToken();
        BlogEntity blogEntity = modelMapper.map(blogRequest, BlogEntity.class);
        blogEntity.setUser(author);
        blogEntity.setBlogStatus(BlogStatusEnum.DRAFT);
        blogEntity.setHashtags(hashtagService.saveAllHashtagsOfBlog(blogRequest));
        blogRepository.save(blogEntity);
        return BuildResponse.buildApiResponse(
                HttpStatus.CREATED.value(),
                "Thành Công!",
                "Bài viết đã được lưu dưới dạng bản nháp!",
                modelMapper.map(blogEntity, BlogResponse.class)
        );
    }

    public ApiResponse<String> getThumbnail(MultipartFile file, String folder) throws URISyntaxException, IOException {
        if (file == null || file.isEmpty()) {
            throw new StorageException("File bị rỗng");
        }

        String fileName = file.getOriginalFilename();
        List<String> allowedFile = Arrays.asList("jpg", "jpeg", "png");
        boolean isValid = allowedFile.stream().anyMatch(fileExtension -> {
            assert fileName != null;
            return fileName.toLowerCase().endsWith(fileExtension);

        });

        if (!isValid) {
            throw new StorageException("Bạn phải truyền file có định dạng: " + allowedFile.toString());
        }
        ApiResponse<String> fileResponse = fileService.uploadImage(file, folder);
        return fileResponse;
    }

    public ApiResponse<BlogResponse> changePublishedOfBlog(Long blogId) {
        BlogEntity blogEntity = blogRepository.findById(blogId).orElseThrow(() -> new NotFoundException("Không tìm thấy bài viết!"));
        if(blogEntity.getBlogStatus().equals(BlogStatusEnum.PUBLISH)) {
            blogEntity.setBlogStatus(BlogStatusEnum.PRIVATE);
        } else {
            blogEntity.setBlogStatus(BlogStatusEnum.PUBLISH);
        }
        blogRepository.save(blogEntity);
        return BuildResponse.buildApiResponse(
                HttpStatus.OK.value(),
                "Thành Công!",
                "Trạng thái bài viết đã được thay đổi!",
                modelMapper.map(blogEntity, BlogResponse.class)
        );
    }

    public ApiResponse<String> deleteBlog(Long blogId) {
        BlogEntity blogEntity = blogRepository.findById(blogId).orElseThrow(() -> new NotFoundException("Không tìm thấy bài viết!"));
        blogRepository.delete(blogEntity);
        return BuildResponse.buildApiResponse(
                HttpStatus.OK.value(),
                "Thành Công!",
                "Xóa bài viết thành công!",
                null
        );
    }
}
