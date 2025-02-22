package com.helper;

import com.dto.response.VideoResponse;
import com.entity.VideoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class VideoServiceHelper {

    public static List<VideoResponse> mapToResponseList(List<VideoEntity> videoEntities) {
        return videoEntities.stream()
                .map(videoEntity -> VideoResponse.builder()
                        .videoId(videoEntity.getVideoId())
                        .title(videoEntity.getTitle())
                        .description(videoEntity.getDescription())
                        .videoUrl(videoEntity.getVideoUrl())
                        .createdAt(videoEntity.getCreatedAt())
                        .updatedAt(videoEntity.getUpdatedAt())
                        .build())
                .toList();
    }
}