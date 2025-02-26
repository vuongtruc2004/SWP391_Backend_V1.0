package com.service;


import com.dto.request.ChapterRequest;
import com.dto.response.ChapterResponse;
import com.entity.ChapterEntity;
import com.repository.ChapterRepository;
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

    public List<ChapterResponse> save(List<ChapterRequest> chapterRequestList) throws Exception {
        List<ChapterResponse> chapterResponseList = new ArrayList<>();
        for (ChapterRequest chapterRequest : chapterRequestList) {
            ChapterEntity newChapter = new ChapterEntity();
            newChapter.setCourse(chapterRequest.getCourse());
            newChapter.setTitle(chapterRequest.getTitle());
            newChapter.setDescription(chapterRequest.getDescription());
            chapterRepository.save(newChapter);
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