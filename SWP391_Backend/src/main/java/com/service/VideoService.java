package com.service;

import com.entity.ChapterEntity;
import com.entity.VideoEntity;
import com.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VideoService {
    private final VideoRepository videoRepository;

    public void deleteVideo(Long id) {
        videoRepository.deleteById(id);
    }

    public void saveVideosWithChapter(List<VideoEntity> videos, ChapterEntity chapter) {
        videos.forEach(video -> {
            video.setDuration(video.getDuration());
            video.setChapter(chapter);
            this.videoRepository.save(video);
        });
    }
}