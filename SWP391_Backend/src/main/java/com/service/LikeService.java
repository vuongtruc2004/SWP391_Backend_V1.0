package com.service;

import com.dto.request.LikeRequest;
import com.dto.response.LikeResponse;
import com.entity.BlogEntity;
import com.entity.CommentEntity;
import com.entity.LikeEntity;
import com.entity.UserEntity;
import com.exception.custom.NotFoundException;
import com.helper.UserServiceHelper;
import com.repository.BlogRepository;
import com.repository.CommentRepository;
import com.repository.LikeRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class LikeService {

    private final LikeRepository likeRepository;
    private final UserServiceHelper userServiceHelper;
    private final BlogRepository blogRepository;
    private final CommentRepository commentRepository;
    private final SimpMessagingTemplate messagingTemplate;
    private final ModelMapper modelMapper;


    public LikeService(LikeRepository likeRepository, UserServiceHelper userServiceHelper, BlogRepository blogRepository, CommentRepository commentRepository, SimpMessagingTemplate messagingTemplate, ModelMapper modelMapper) {
        this.likeRepository = likeRepository;
        this.userServiceHelper = userServiceHelper;
        this.blogRepository = blogRepository;
        this.commentRepository = commentRepository;
        this.messagingTemplate = messagingTemplate;
        this.modelMapper = modelMapper;
    }

    public void newLike(){
        messagingTemplate.convertAndSend("/topic/like", "new like");
    }

    public LikeResponse likeABlogOrComment(LikeRequest likeRequest) {
        UserEntity userEntity = userServiceHelper.extractUserFromToken();
        if(userEntity == null) {
            throw new NotFoundException("Lỗi đăng nhập!");
        }
        LikeEntity likeEntity = new LikeEntity();
        likeEntity.setUser(userEntity);

        if(likeRequest.getBlogId()!= null){
           BlogEntity blogEntity = blogRepository.findById(likeRequest.getBlogId()).get();
           if(blogEntity != null) {
               likeEntity.setBlog(blogEntity);
           } else {
               throw new NotFoundException("Không tìm thấy bài viết hoặc bài viết đã bị xóa!");
           }
        }


        if(likeRequest.getCommentId() != null){
            CommentEntity comment = commentRepository.findById(likeRequest.getCommentId()).get();
            if(comment != null) {
                likeEntity.setComment(comment);
            } else {
                throw new NotFoundException("Bình luận đã bị xóa hoặc không tồn tại!");
            }
        }

        likeRepository.save(likeEntity);
        LikeResponse likeResponse = modelMapper.map(likeEntity, LikeResponse.class);
        newLike();
        return likeResponse;
    }

    //dislike 1 blog
    @Transactional
    public void dislikeABlog (Long blogId) {
        UserEntity userEntity = userServiceHelper.extractUserFromToken();
        if(userEntity == null) {
            throw new NotFoundException("Không thể lấy thông tin người dùng!");
        }

        LikeEntity exist = likeRepository.findByUser_UserIdAndBlog_BlogId(userEntity.getUserId(), blogId);
        if(exist != null) {
            likeRepository.delete(exist);
        }
        newLike();
    }

    //dislike a comment
    @Transactional
    public void dislikeAComment(Long commentId) {
        UserEntity userEntity = userServiceHelper.extractUserFromToken();
        if(userEntity == null) {
            throw new NotFoundException("Không thể lấy thông tin người dùng!");
        }

        LikeEntity exist = likeRepository.findByUser_UserIdAndComment_CommentId(userEntity.getUserId(), commentId);
        if(exist != null){
            likeRepository.delete(exist);
        }
    }

    //check user da like hay chua
    public Boolean isLiked(Long blogId) {
        UserEntity userEntity = userServiceHelper.extractUserFromToken();
        if(userEntity == null) {
            throw new NotFoundException("Không tìm thấy người dùng!");
        }
        Boolean exist = likeRepository.existsByUser_UserIdAndBlog_BlogId(userEntity.getUserId(), blogId);
        return exist;
    }

    //kiem tra xem comment ay da duoc nguoi dung dang nhap hay chua
    public Boolean isLikedComment(Long commentId) {
        UserEntity userEntity = userServiceHelper.extractUserFromToken();
        if(userEntity == null) {
            throw new NotFoundException("Không tìm thấy người dùng!");
        }

        return likeRepository.existsByUser_UserIdAndComment_CommentId(userEntity.getUserId(), commentId);
    }
}
