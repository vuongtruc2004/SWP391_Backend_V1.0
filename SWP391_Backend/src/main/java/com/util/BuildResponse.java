package com.util;

import com.dto.request.ChapterRequest;
import com.dto.response.*;
import com.entity.ChapterEntity;
import com.util.enums.LessonTypeEnum;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

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
        List<LessonResponse> lessonResponses = chapterRequest.getLessons().stream()
                .map(lessonRequest -> {
                    LessonResponse lessonResponse = new LessonResponse();
                    lessonResponse.setTitle(lessonRequest.getTitle());
                    if(lessonRequest.getLessonType().equals(LessonTypeEnum.DOCUMENT)){
                        lessonResponse.setLessonType(LessonTypeEnum.DOCUMENT);
                        lessonResponse.setDocumentContent(lessonRequest.getDocumentContent());
                        lessonResponse.setDuration(LessonServiceHelper.countDuration(lessonResponse.getDocumentContent()));
                    }else{
                        lessonResponse.setLessonType(LessonTypeEnum.VIDEO);
                        lessonResponse.setVideoUrl(lessonRequest.getVideoUrl());
                        lessonResponse.setDuration(lessonRequest.getDuration());
                    }
                    return lessonResponse;
                })
                .collect(Collectors.toList());
        chapterResponse.setLessons(lessonResponses);
        return chapterResponse;
    }
}
