package com.service;



import com.dto.request.ChapterRequest;
import com.dto.response.ChapterResponse;
import com.entity.ChapterEntity;
import com.helper.DocumentServiceHelper;
import com.helper.VideoServiceHelper;
import com.repository.DocumentRepository;
import com.repository.ChapterRepository;
import com.repository.VideoRepository;
import com.util.BuildResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ChapterService {
    private final ChapterRepository chapterRepository;
    private final DocumentService documentService;
    private final VideoService videoService;

    public List<ChapterResponse> save(List<ChapterRequest> chapterRequestList) throws Exception {
        List<ChapterResponse> chapterResponseList =  new ArrayList<>();
        for(ChapterRequest chapterRequest : chapterRequestList){
            ChapterEntity newChapter = new ChapterEntity();
            newChapter.setCourse(chapterRequest.getCourse());
            newChapter.setTitle(chapterRequest.getTitle());
            this.chapterRepository.save(newChapter);
            this.documentService.saveDocumentsWithChapter(chapterRequest.getDocuments(), newChapter);
            this.videoService.saveVideosWithChapter(chapterRequest.getVideos(), newChapter);
            chapterResponseList.add(BuildResponse.buildChapterResponse(chapterRequest, newChapter));
            this.chapterRepository.save(newChapter);

        }
        return chapterResponseList;
    }

    @Transactional
    public void deleteChapter(Long chapterId)  {
        this.chapterRepository.deleteById(chapterId);
    }


}