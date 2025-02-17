package com.controller.api.v1;


import com.dto.request.LessonRequest;
import com.dto.response.LessonResponse;
import com.service.LessonService;
import com.util.annotation.ApiMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/lessons")
@RequiredArgsConstructor
public class LessonController {
    private final LessonService lessonService;

    @DeleteMapping("/{id}")
    @ApiMessage("Xoá bài giảng thành công")
    public ResponseEntity<Void> deleteLesson(@PathVariable  Long id) {
        this.lessonService.deleteLesson(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    @ApiMessage("Tạo mới bài giảng thành công")
    public ResponseEntity<List<LessonResponse>> createLesson(@RequestBody List<LessonRequest> lessonRequests) throws Exception {
        return ResponseEntity.ok().body(this.lessonService.save(lessonRequests));
    }

}
