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
        UserProgressEntity userProgressEntity;
        if (userProgressRequest.getDocumentId() != null) {
            userProgressEntity = userProgressRepository.findByUserIdAndCourseIdAndLessonIdAndDocumentId(
                    userEntity.getUserId(),
                    userProgressRequest.getCourseId(),
                    userProgressRequest.getLessonId(),
                    userProgressRequest.getDocumentId()
            ).orElse(null);
        } else if (userProgressRequest.getVideoId() != null) {
            userProgressEntity = userProgressRepository.findByUserIdAndCourseIdAndLessonIdAndVideoId(
                    userEntity.getUserId(),
                    userProgressRequest.getCourseId(),
                    userProgressRequest.getLessonId(),
                    userProgressRequest.getVideoId()
            ).orElse(null);
        } else {
            throw new InvalidRequestInput("Thiếu thông tin về videoId hoặc documentId");
        }
        if (userProgressEntity == null) {
            UserProgressEntity newUserProgressEntity = UserProgressEntity.builder()
                    .userId(userEntity.getUserId())
                    .courseId(userProgressRequest.getCourseId())
                    .lessonId(userProgressRequest.getLessonId())
                    .documentId(userProgressRequest.getDocumentId())
                    .videoId(userProgressRequest.getVideoId())
                    .build();
            notificationService.purchaseSuccessNotification();
            return userProgressRepository.save(newUserProgressEntity);
        } else {
            userProgressRepository.delete(userProgressEntity);
            notificationService.purchaseSuccessNotification();
            return userProgressEntity;
        }
    }
}
