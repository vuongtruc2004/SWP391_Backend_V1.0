package com.service;

import com.dto.request.LessonRequest;
import com.entity.ChapterEntity;
import com.entity.CourseEntity;
import com.entity.LessonEntity;
import com.exception.custom.CourseException;
import com.exception.custom.InvalidTokenException;
import com.exception.custom.NotFoundException;
import com.repository.ChapterRepository;
import com.repository.CourseRepository;
import com.repository.LessonRepository;
import com.util.enums.CourseStatusEnum;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LessonService {

    private final ChapterRepository chapterRepository;
    private final ModelMapper modelMapper;
    private final LessonRepository lessonRepository;
    private final CourseRepository courseRepository;

    public void createUpdateLesson(LessonRequest lessonRequest) {
        ChapterEntity chapter = chapterRepository.findById(lessonRequest.getChapterId())
                .orElseThrow(() -> new NotFoundException("Chương học không tồn tại!"));
        if (lessonRequest.getLessonId() == null) {
            LessonEntity lessonEntity = modelMapper.map(lessonRequest, LessonEntity.class);
            lessonEntity.setChapter(chapter);
            lessonRepository.save(lessonEntity);
        } else {
            LessonEntity currentLesson = lessonRepository.findById(lessonRequest.getLessonId())
                    .orElseThrow(() -> new NotFoundException("Bài học không tồn tại!"));
            if (!currentLesson.getLessonType().equals(lessonRequest.getLessonType())) {
                throw new CourseException("Bài học không hợp lệ!");
            }
            currentLesson.setTitle(lessonRequest.getTitle());
            currentLesson.setDescription(lessonRequest.getDescription());
            currentLesson.setDocumentContent(lessonRequest.getDocumentContent());
            currentLesson.setVideoUrl(lessonRequest.getVideoUrl());
            currentLesson.setDuration(lessonRequest.getDuration());
            lessonRepository.save(currentLesson);
        }
    }

    public void deleteLesson(Long lessonId) {
        if (lessonId == null) {
            throw new InvalidTokenException("LessonId không được null!");
        }
        LessonEntity lessonEntity = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new NotFoundException("Bài giảng không tồn tại!"));
        CourseEntity course = lessonEntity.getChapter().getCourse();
        course.setCourseStatus(CourseStatusEnum.DRAFT);
        courseRepository.save(course);
        lessonRepository.deleteById(lessonId);
    }
}
