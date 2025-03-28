package com.service;

import com.dto.request.CourseChapterRequest;
import com.dto.response.ChapterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChapterService {

    public List<ChapterResponse> saveChapterOfACourse(CourseChapterRequest courseChapterRequest) {

        return null;
    }
}
