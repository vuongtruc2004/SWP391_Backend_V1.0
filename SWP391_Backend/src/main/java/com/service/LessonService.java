package com.service;


import com.entity.CourseEntity;
import com.entity.DocumentEntity;
import com.entity.LessonEntity;
import com.repository.CourseRepository;
import com.repository.DocumentRepository;
import com.repository.LessonRepository;
import com.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class LessonService {
    private final LessonRepository lessonRepository;
    private final DocumentRepository documentRepository;
    private final VideoRepository videoRepository;
    private final CourseRepository courseRepository;
    public void save(LessonEntity lesson) {
        lesson.setTitle(lesson.getTitle());
        lesson.setDescription(lesson.getDescription());
        lessonRepository.save(lesson);
    }

}
