package com.controller.api.v1;

import com.dto.request.AnswerRequest;
import com.dto.response.AnswerResponse;
import com.dto.response.ApiResponse;
import com.service.AnswerService;
import com.util.annotation.ApiMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/answers")
@RequiredArgsConstructor
public class AnswerController {
    private final AnswerService answerService;

    @ApiMessage("Tạo câu trả lời thành công!")
    @PostMapping
    public ResponseEntity<ApiResponse<AnswerResponse>> createSubject(@RequestBody AnswerRequest answerRequest) {
        return ResponseEntity.ok(answerService.createAnswers(answerRequest));
    }

    @ApiMessage("Cập nhật câu trả lời thành công!")
    @PatchMapping("/update/{answerId}")
    public ResponseEntity<ApiResponse<AnswerResponse>> createSubject(@PathVariable Long answerId,@RequestBody AnswerRequest answerRequest) {
        return ResponseEntity.ok(answerService.updateAnswers(answerId, answerRequest));
    }

    @ApiMessage("Xóa câu trả lời thành công!")
    @DeleteMapping("/delete/{answerId}")
    public ResponseEntity<ApiResponse<String>> deleteSubject(@PathVariable Long answerId) {
        return ResponseEntity.ok(answerService.deleteAnswer(answerId));
    }

}
