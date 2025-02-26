package com.service;

import com.dto.request.LessonRequest;
import com.entity.ChapterEntity;
import com.entity.LessonEntity;
import com.repository.LessonRepository;
import com.util.enums.LessonTypeEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonService {
    private final LessonRepository lessonRepository;
    public void saveLessonWithChapter(List<LessonRequest> lessonRequests, ChapterEntity chapterEntity) {
        for (LessonRequest lessonRequest : lessonRequests) {
            LessonEntity saveLessonEntity=new LessonEntity();
            saveLessonEntity.setChapter(chapterEntity);
            saveLessonEntity.setTitle(lessonRequest.getTitle());
            if(lessonRequest.getLessonType().equals(LessonTypeEnum.DOCUMENT)){
                saveLessonEntity.setLessonType(LessonTypeEnum.DOCUMENT);
                saveLessonEntity.setDocumentContent(lessonRequest.getDocumentContent());
                saveLessonEntity.setDuration(lessonRequest.getDuration());
            }else if(lessonRequest.getLessonType().equals(LessonTypeEnum.VIDEO)){
                saveLessonEntity.setLessonType(LessonTypeEnum.VIDEO);
                saveLessonEntity.setDuration(lessonRequest.getDuration());
                saveLessonEntity.setVideoUrl(lessonRequest.getVideoUrl());
            }
            this.lessonRepository.save(saveLessonEntity);
        }
    }
}
