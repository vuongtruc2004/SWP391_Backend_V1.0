package com.service;

import com.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VideoService {
    private final VideoRepository videoRepository;
    public void deleteVideo(Long id){
        videoRepository.deleteById(id);
    }
}
