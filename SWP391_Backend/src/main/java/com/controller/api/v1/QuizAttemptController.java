package com.controller.api.v1;

import com.dto.request.QuizAttemptRequest;
import com.dto.response.QuizAttemptResponse;
import com.service.QuizAttemptService;
import com.util.annotation.ApiMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/quiz-attempts")
@RequiredArgsConstructor
public class QuizAttemptController {
    private final QuizAttemptService quizAttemptService;

    @ApiMessage("Tạo quizAttempt thành công")
    @PostMapping("/{quizId}")
    public ResponseEntity<QuizAttemptResponse> createQuizAttempt(@PathVariable Long quizId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(quizAttemptService.createNewQuizAttempt(quizId));
    }

    @ApiMessage("Lưu bài làm thành công")
    @PostMapping("/save")
    public ResponseEntity<QuizAttemptResponse> saveQuizAttempt(@RequestBody QuizAttemptRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(quizAttemptService.saveQuizAttempt(request));
    }

    @ApiMessage("Nộp bài kiểm tra thành công")
    @PostMapping("/submit")
    public ResponseEntity<QuizAttemptResponse> submitQuizAttempt(@RequestBody QuizAttemptRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(quizAttemptService.submitQuizAttempt(request));
    }
}
