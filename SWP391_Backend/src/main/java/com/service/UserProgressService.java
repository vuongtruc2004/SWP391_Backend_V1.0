package com.service;

import com.dto.request.UserProgressRequest;
import com.dto.response.UserProgressResponse;
import com.entity.UserEntity;
import com.entity.UserProgressEntity;
import com.exception.custom.InvalidRequestInput;
import com.exception.custom.UserException;
import com.helper.UserServiceHelper;
import com.repository.UserProgressRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserProgressService {

    private final UserProgressRepository userProgressRepository;
    private final UserServiceHelper userServiceHelper;
    private final ModelMapper modelMapper;

    public Set<UserProgressResponse> getUserProgresses() {
        UserEntity userEntity = userServiceHelper.extractUserFromToken();
        if (userEntity == null) {
            throw new UserException("Vui lòng đăng nhập!");
        }
        return userEntity.getUserProgresses()
                .stream().map(userProgressEntity -> {
                    UserProgressResponse userProgressResponse = modelMapper.map(userProgressEntity, UserProgressResponse.class);
                    userProgressResponse.setUserId(userEntity.getUserId());
                    return userProgressResponse;
                })
                .collect(Collectors.toSet());
    }

    public UserProgressResponse changeStatus(UserProgressRequest userProgressRequest) {
        UserEntity userEntity = userServiceHelper.extractUserFromToken();
        if (userEntity == null) {
            throw new UserException("Vui lòng đăng nhập!");
        }

        UserProgressEntity userProgressEntity = new UserProgressEntity();
        userProgressEntity.setUser(userEntity);
        userProgressEntity.setCourseId(userProgressRequest.getCourseId());
        userProgressEntity.setChapterId(userProgressRequest.getChapterId());

        if ((userProgressRequest.getLessonId() == null && userProgressRequest.getQuizId() == null) ||
                (userProgressRequest.getLessonId() != null && userProgressRequest.getQuizId() != null)) {
            throw new InvalidRequestInput("Bạn chỉ được truyền 1 trong hai tham số lessonId hoặc quizId!");
        } else if (userProgressRequest.getQuizId() != null) {
            if (userProgressRepository.existsByUser_UserIdAndCourseIdAndChapterIdAndQuizId(
                    userEntity.getUserId(),
                    userProgressRequest.getCourseId(),
                    userProgressRequest.getChapterId(),
                    userProgressRequest.getQuizId())
            ) {
                throw new InvalidRequestInput("Bài quiz này đã được người dùng hoàn thành!");
            }
            userProgressEntity.setQuizId(userProgressRequest.getQuizId());
        } else {
            if (userProgressRepository.existsByUser_UserIdAndCourseIdAndChapterIdAndLessonId(
                    userEntity.getUserId(),
                    userProgressRequest.getCourseId(),
                    userProgressRequest.getChapterId(),
                    userProgressRequest.getLessonId())
            ) {
                throw new InvalidRequestInput("Bài giảng này đã được người dùng hoàn thành!");
            }
            userProgressEntity.setLessonId(userProgressRequest.getLessonId());
        }

        return modelMapper.map(userProgressRepository.save(userProgressEntity), UserProgressResponse.class);
    }
}
