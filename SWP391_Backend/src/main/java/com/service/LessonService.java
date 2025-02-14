package com.service;



import com.entity.DocumentEntity;
import com.entity.LessonEntity;
import com.entity.VideoEntity;
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

    public void save(LessonEntity lesson) throws Exception {
        LessonEntity newLesson = this.lessonRepository.save(lesson);
        newLesson.setTitle(lesson.getTitle());
        newLesson.setDescription(lesson.getDescription());
        for (DocumentEntity documentEntity : lesson.getDocuments()) {
            documentEntity.setLesson(newLesson);
            this.documentRepository.save(documentEntity);
        }
        for (VideoEntity videoEntity : lesson.getVideos()) {
            videoEntity.setLesson(newLesson);
            this.videoRepository.save(videoEntity);
        }
        this.lessonRepository.save(newLesson);
    }


}