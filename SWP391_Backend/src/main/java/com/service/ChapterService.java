package com.service;


import com.dto.request.ChapterRequest;
import com.dto.response.ChapterResponse;
import com.entity.ChapterEntity;
import com.entity.CourseEntity;
import com.exception.custom.NotFoundException;
import com.repository.ChapterRepository;
import com.repository.CourseRepository;
import com.util.BuildResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ChapterService {
    private final ChapterRepository chapterRepository;
    private final LessonService lessonService;
    private final ModelMapper modelMapper;
    private final CourseRepository courseRepository;

    public List<ChapterResponse> save(List<ChapterRequest> chapterRequestList) throws Exception {
        List<ChapterResponse> chapterResponseList = new ArrayList<>();
        for (ChapterRequest chapterRequest : chapterRequestList) {
            ChapterEntity newChapter = new ChapterEntity();
            CourseEntity course = courseRepository.findById(chapterRequest.getCourseId())
                    .orElseThrow(()-> new NotFoundException("Not found!"));
            newChapter.setCourse(course);
            modelMapper.map(chapterRequest, newChapter);
            chapterRepository.save(newChapter);
            this.lessonService.saveLessonWithChapter(chapterRequest.getLessons(), newChapter);
            chapterResponseList.add(BuildResponse.buildChapterResponse(chapterRequest, newChapter));
            chapterRepository.save(newChapter);
        }
        return chapterResponseList;
    }

    @Transactional
    public void deleteChapter(Long chapterId) {
        chapterRepository.deleteById(chapterId);
    }
}