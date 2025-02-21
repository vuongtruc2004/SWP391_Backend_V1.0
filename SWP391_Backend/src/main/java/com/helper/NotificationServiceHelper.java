package com.helper;

import com.dto.response.NotificationResponse;
import com.dto.response.PageDetailsResponse;
import com.dto.response.UserNotificationResponse;
import com.entity.UserNotificationEntity;
import com.util.BuildResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class NotificationServiceHelper {

    private final ModelMapper modelMapper;

    public PageDetailsResponse<List<UserNotificationResponse>> convertToPageDetailsResponse(Page<UserNotificationEntity> page) {
        List<UserNotificationResponse>notificationResponseList = page.getContent().stream()
                .map(userNotificationEntity -> modelMapper.map(userNotificationEntity, UserNotificationResponse.class))
                .toList();
         return BuildResponse.buildPageDetailsResponse(
                page.getNumber()+1,
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements(),
                 notificationResponseList
                );
    }
}
