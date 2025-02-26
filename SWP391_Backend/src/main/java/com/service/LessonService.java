package com.service;

import com.dto.request.LessonRequest;
import com.entity.ChapterEntity;
import com.entity.LessonEntity;
import com.repository.LessonRepository;
import com.util.enums.LessonTypeEnum;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LessonService {
    private final LessonRepository lessonRepository;
    private final ModelMapper modelMapper;
    public void saveLessonWithChapter(List<LessonRequest> lessonRequests, ChapterEntity chapterEntity) {
        List<LessonEntity> lessonEntities = lessonRequests.stream().map(lessonRequest -> {
            LessonEntity lessonEntity = modelMapper.map(lessonRequest, LessonEntity.class);
            lessonEntity.setChapter(chapterEntity);
            return lessonEntity;
        }).collect(Collectors.toList());
        lessonRepository.saveAll(lessonEntities);
    }

}
