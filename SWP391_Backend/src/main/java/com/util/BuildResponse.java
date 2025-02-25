package com.util;

import com.dto.request.ChapterRequest;
import com.dto.response.*;
import com.entity.ChapterEntity;
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

    public static ChapterResponse buildChapterResponse(ChapterRequest chapterRequest, ChapterEntity newChapter) {
        ChapterResponse chapterResponse = new ChapterResponse();
        chapterResponse.setChapterId(newChapter.getChapterId());
        chapterResponse.setTitle(chapterRequest.getTitle());
        chapterResponse.setDocuments(DocumentServiceHelper.mapToResponseList(chapterRequest.getDocuments()));
        chapterResponse.setVideos(VideoServiceHelper.mapToResponseList(chapterRequest.getVideos()));
        return chapterResponse;
    }
}
