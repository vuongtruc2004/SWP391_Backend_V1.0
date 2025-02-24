package com.service;

import com.dto.request.UserProgressRequest;
import com.entity.UserEntity;
import com.entity.UserProgressEntity;
import com.exception.custom.InvalidRequestInput;
import com.exception.custom.UserException;
import com.helper.UserServiceHelper;
import com.repository.UserProgressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserProgressService {

    private final UserProgressRepository userProgressRepository;
    private final UserServiceHelper userServiceHelper;
    private final NotificationService notificationService;

    public List<UserProgressEntity> getUserProgressByCourseId(Long courseId) {
        UserEntity userEntity = userServiceHelper.extractUserFromToken();
        return userProgressRepository.findAllByCourseIdAndUserId(courseId, userEntity.getUserId());
    }

    public UserProgressEntity changeStatus(UserProgressRequest userProgressRequest) {
        UserEntity userEntity = userServiceHelper.extractUserFromToken();
        if (userEntity == null) {
            throw new UserException("Vui lòng đăng nhập!");
        }
        if (userProgressRepository.existsByUserIdAndCourseIdAndLessonIdAndDocumentIdAndVideoId(
                userEntity.getUserId(),
                userProgressRequest.getCourseId(),
                userProgressRequest.getLessonId(),
                userProgressRequest.getDocumentId(),
                userProgressRequest.getVideoId()
        )) {
            throw new InvalidRequestInput("Bài học này đã hoàn thành!");
        }
        UserProgressEntity newUserProgressEntity = UserProgressEntity.builder()
                .userId(userEntity.getUserId())
                .courseId(userProgressRequest.getCourseId())
                .lessonId(userProgressRequest.getLessonId())
                .documentId(userProgressRequest.getDocumentId())
                .videoId(userProgressRequest.getVideoId())
                .build();
        notificationService.purchaseSuccessNotification();
        return userProgressRepository.save(newUserProgressEntity);
    }
}
