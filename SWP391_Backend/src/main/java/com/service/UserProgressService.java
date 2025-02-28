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
    private final NotificationService notificationService;
    private final ModelMapper modelMapper;

    public Set<UserProgressResponse> getUserProgresses() {
        UserEntity userEntity = userServiceHelper.extractUserFromToken();
        if (userEntity == null) {
            throw new UserException("Vui lòng đăng nhập!");
        }
        return userEntity.getUserProgresses()
                .stream().map(userProgressEntity -> modelMapper.map(userProgressEntity, UserProgressResponse.class))
                .collect(Collectors.toSet());
    }

    public UserProgressResponse changeStatus(UserProgressRequest userProgressRequest) {
        UserEntity userEntity = userServiceHelper.extractUserFromToken();
        if (userEntity == null) {
            throw new UserException("Vui lòng đăng nhập!");
        }
        if (userProgressRepository.existsByUser_UserIdAndChapterIdAndLessonId(
                userEntity.getUserId(),
                userProgressRequest.getChapterId(),
                userProgressRequest.getLessonId()
        )) {
            throw new InvalidRequestInput("Bài học này đã hoàn thành!");
        }

        UserProgressEntity newUserProgressEntity = UserProgressEntity.builder()
                .user(userEntity)
                .courseId(userProgressRequest.getCourseId())
                .chapterId(userProgressRequest.getChapterId())
                .lessonId(userProgressRequest.getLessonId())
                .build();

        notificationService.purchaseSuccessNotification();
        return modelMapper.map(userProgressRepository.save(newUserProgressEntity), UserProgressResponse.class);
    }
}
