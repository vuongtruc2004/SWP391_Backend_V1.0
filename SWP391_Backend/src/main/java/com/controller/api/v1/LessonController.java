package com.controller.api.v1;


import com.service.LessonService;
import com.util.annotation.ApiMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
