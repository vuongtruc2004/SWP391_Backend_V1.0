package com.controller.api.v1;


import com.dto.request.ChapterRequest;
import com.dto.response.ChapterResponse;
import com.service.ChapterService;
import com.util.annotation.ApiMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/chapters")
@RequiredArgsConstructor
public class ChapterController {

    private final ChapterService chapterService;
    private final Logger logger= Logger.getLogger(this.getClass().getName());
    @DeleteMapping("/{id}")
    @ApiMessage("Xoá bài giảng thành công")
    public ResponseEntity<Void> deleteChapter(@PathVariable Long id) {
        chapterService.deleteChapter(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    @ApiMessage("Tạo mới bài giảng thành công")
    public ResponseEntity<List<ChapterResponse>> createChapter(@RequestBody List<ChapterRequest> chapterRequests) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.chapterService.save(chapterRequests));
    }
}