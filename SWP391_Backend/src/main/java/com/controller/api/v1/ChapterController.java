package com.controller.api.v1;

import com.dto.request.ChapterRequest;
import com.service.ChapterService;
import com.util.annotation.ApiMessage;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/chapters")
@RequiredArgsConstructor
public class ChapterController {

    private final ChapterService chapterService;

    @ApiMessage("Tạo mới/Cập nhật chương học thành công!")
    @PutMapping
    public ResponseEntity<Void> createUpdateChapter(@Valid @RequestBody ChapterRequest chapterRequest) {
        chapterService.createUpdateChapter(chapterRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiMessage("Xóa chương học thành công!")
    @DeleteMapping("/{chapterId}")
    public ResponseEntity<Void> deleteChapter(@PathVariable Long chapterId) {
        chapterService.deleteChapter(chapterId);
        return ResponseEntity.ok().build();
    }
}
