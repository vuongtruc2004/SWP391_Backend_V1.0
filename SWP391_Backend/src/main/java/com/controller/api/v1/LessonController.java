package com.controller.api.v1;

import com.dto.request.LessonRequest;
import com.service.LessonService;
import com.util.annotation.ApiMessage;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/lessons")
@RequiredArgsConstructor
public class LessonController {

    private final LessonService lessonService;

    @ApiMessage("Tạo/Cập nhật bài giảng thành công!")
    @PutMapping
    public ResponseEntity<Void> createUpdateLesson(@Valid @RequestBody LessonRequest lessonRequest) {
        lessonService.createUpdateLesson(lessonRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiMessage("Xóa bài giảng thành công!")
    @DeleteMapping("/{lessonId}")
    public ResponseEntity<Void> deleteLesson(@PathVariable Long lessonId) {
        lessonService.deleteLesson(lessonId);
        return ResponseEntity.ok().build();
    }
}
