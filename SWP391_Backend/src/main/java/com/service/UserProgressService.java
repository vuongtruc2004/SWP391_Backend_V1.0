package com.service;

import com.entity.UserEntity;
import com.entity.UserProgressEntity;
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

    public List<UserProgressEntity> getUserProgressByCourseId(Long courseId) {
        UserEntity userEntity = userServiceHelper.extractUserFromToken();
        return userProgressRepository.findAllByCourseIdAndUserId(courseId, userEntity.getUserId());
    }
}
