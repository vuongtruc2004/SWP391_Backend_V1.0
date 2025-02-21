package com.service;



import com.dto.request.LessonRequest;
import com.dto.response.CourseResponse;
import com.dto.response.LessonResponse;
import com.entity.CourseEntity;
import com.entity.DocumentEntity;
import com.entity.LessonEntity;
import com.entity.VideoEntity;
import com.helper.DocumentServiceHelper;
import com.helper.VideoServiceHelper;
import com.repository.CourseRepository;
import com.repository.DocumentRepository;
import com.repository.LessonRepository;
import com.repository.VideoRepository;
import com.util.BuildResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class LessonService {
    private final LessonRepository lessonRepository;
    private final DocumentRepository documentRepository;
    private final VideoRepository videoRepository;
    private final DocumentServiceHelper documentServiceHelper;
    private final VideoServiceHelper videoServiceHelper;
    private final DocumentService documentService;
    private final VideoService videoService;

    public List<LessonResponse> save(List<LessonRequest> lessonRequestList) throws Exception {
        List<LessonResponse> lessonResponseList =  new ArrayList<>();
        for(LessonRequest lessonRequest : lessonRequestList){
            LessonEntity newLesson = new LessonEntity();
            newLesson.setCourse(lessonRequest.getCourse());
            newLesson.setTitle(lessonRequest.getTitle());
            this.lessonRepository.save(newLesson);
            this.documentService.saveDocumentsWithLesson(lessonRequest.getDocuments(), newLesson);
            this.videoService.saveVideosWithLesson(lessonRequest.getVideos(), newLesson);
            lessonResponseList.add(BuildResponse.buildLessonResponse(lessonRequest, newLesson));
            this.lessonRepository.save(newLesson);

        }
        return lessonResponseList;
    }

    @Transactional
    public void deleteLesson(Long lessonsId)  {
        this.lessonRepository.deleteById(lessonsId);
    }


}