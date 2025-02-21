package com.util;

import com.dto.request.LessonRequest;
import com.dto.response.*;
import com.entity.LessonEntity;
import com.helper.DocumentServiceHelper;
import com.helper.VideoServiceHelper;

import java.time.Instant;

public class BuildResponse {

    public static <T> ApiResponse<T> buildApiResponse(Integer status, Object message, String errorMessage, T data) {
        return ApiResponse.<T>builder()
                .status(status)
                .message(message)
                .errorMessage(errorMessage)
                .data(data)
                .build();
    }

    public static LoginResponse buildLoginResponse(UserResponse userResponse, String accessToken, Instant expireAt, String refreshToken) {
        return LoginResponse.builder()
                .user(userResponse)
                .accessToken(accessToken)
                .expireAt(expireAt)
                .refreshToken(refreshToken)
                .build();
    }

    public static <T> PageDetailsResponse<T> buildPageDetailsResponse(
            Integer currentPage,
            Integer pageSize,
            Integer totalPages,
            Long totalElements,
            T content
    ) {
        return PageDetailsResponse.<T>builder()
                .currentPage(currentPage)
                .pageSize(pageSize)
                .totalPages(totalPages)
                .totalElements(totalElements)
                .content(content)
                .build();
    }
    public static LessonResponse buildLessonResponse(LessonRequest lessonRequest, LessonEntity newLesson) {
        LessonResponse lessonResponse = new LessonResponse();
        lessonResponse.setLessonId(newLesson.getLessonId());
        lessonResponse.setTitle(lessonRequest.getTitle());
        lessonResponse.setDocuments(DocumentServiceHelper.mapToResponseSet(lessonRequest.getDocuments()));
        lessonResponse.setVideos(VideoServiceHelper.mapToResponseSet(lessonRequest.getVideos()));
        return lessonResponse;
    }
}
