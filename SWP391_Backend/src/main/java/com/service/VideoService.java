package com.service;

import com.entity.LessonEntity;
import com.entity.VideoEntity;
import com.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class VideoService {
    private final VideoRepository videoRepository;
    public void deleteVideo(Long id){
        videoRepository.deleteById(id);
    }
    public void saveVideosWithLesson(Set<VideoEntity> videos, LessonEntity lesson) {
        videos.forEach(video -> {
            video.setLesson(lesson);
            this.videoRepository.save(video);
        });
    }
}
