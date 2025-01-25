package com.util;

import com.dto.response.ApiResponse;
import com.dto.response.PageDetailsResponse;

public class BuildResponse {

    public static <T> ApiResponse<T> buildApiResponse(Integer status, String message, T data) {
        return ApiResponse.<T>builder()
                .status(status)
                .message(message)
                .data(data)
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
}
