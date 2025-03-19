package com.controller.api.v1;

import com.dto.request.QuizAttemptRequest;
import com.dto.response.QuizAttemptResponse;
import com.service.QuizAttemptService;
import com.util.annotation.ApiMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/quizzes-attempt")
@RequiredArgsConstructor
public class QuizAttemptController {
    private final QuizAttemptService quizAttemptService;

    @ApiMessage("Lưu câu trả lời thành công")
    @PostMapping("/save")
    public ResponseEntity<QuizAttemptResponse> saveQuizAttempt(
            @RequestBody QuizAttemptRequest quizAttemptRequest,
            @RequestParam(required = false, defaultValue = "false") boolean isSubmit) {

        QuizAttemptResponse response = quizAttemptService.saveQuizAttempt(quizAttemptRequest, isSubmit);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/{userId}/{quizId}")
    public ResponseEntity<QuizAttemptResponse> getQuizAttempt(
            @PathVariable Long userId,
            @PathVariable Long quizId) {
        QuizAttemptResponse response = quizAttemptService.getQuizAttemptByUserAndQuiz(userId, quizId);
        return ResponseEntity.ok(response);
    }
}
