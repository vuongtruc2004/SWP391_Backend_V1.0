package com.controller.api.v1;

import com.dto.request.QuizRequest;
import com.dto.response.QuizResponse;
import com.service.QuizService;
import com.util.annotation.ApiMessage;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/quizzes")
@RequiredArgsConstructor
public class QuizController {
    private final QuizService quizService;

    @ApiMessage("Mở bài quiz thành công!")
    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> publishedQuiz(@PathVariable Long id) {
        return ResponseEntity.ok(quizService.published(id));
    }

    @ApiMessage("Tạo bài kiểm tra thành công!")
    @PostMapping
    public ResponseEntity<QuizResponse> createQuiz(@RequestBody @Valid QuizRequest quizRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(quizService.createQuiz(quizRequest));
    }

    @ApiMessage("Lấy bài kiểm tra thành công!")
    @GetMapping("/{quizId}")
    public ResponseEntity<QuizResponse> getQuizByQuizId(@PathVariable Long quizId) {
        return ResponseEntity.ok(quizService.getQuizByQuizId(quizId));
    }
}
