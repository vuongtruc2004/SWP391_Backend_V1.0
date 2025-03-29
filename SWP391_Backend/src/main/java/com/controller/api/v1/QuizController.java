package com.controller.api.v1;

import com.dto.request.QuizRequest;
import com.dto.response.ChapterResponse;
import com.dto.response.PageDetailsResponse;
import com.dto.response.QuizResponse;
import com.entity.QuizEntity;
import com.service.QuizService;
import com.turkraft.springfilter.boot.Filter;
import com.util.annotation.ApiMessage;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/quizzes")
@RequiredArgsConstructor
public class QuizController {
    private final QuizService quizService;

    @ApiMessage("Mở bài quiz thành công!")
    @PatchMapping("{id}")
    public ResponseEntity<Boolean> publishedQuiz(@PathVariable Long id) {
        return ResponseEntity.ok(quizService.published(id));
    }

    @ApiMessage("Tạo bài kiểm tra thành công!")
    @PostMapping
    public ResponseEntity<QuizResponse> createQuiz(@RequestBody @Valid QuizRequest quizRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(quizService.createQuiz(quizRequest));
    }

    @ApiMessage("Cập nhật bài kiểm tra thành công!")
    @PutMapping
    public ResponseEntity<QuizResponse> updateQuiz(@RequestBody @Valid QuizRequest quizRequest) {
        return ResponseEntity.ok(quizService.updateQuiz(quizRequest));
    }

    @ApiMessage("Lấy bài kiểm tra thành công!")
    @GetMapping("/{quizId}")
    public ResponseEntity<QuizResponse> getQuizByQuizId(@PathVariable Long quizId) {
        return ResponseEntity.ok(quizService.getQuizByQuizId(quizId));
    }

    @ApiMessage("Lấy tất cả bài kiểm tra thành công")
    @GetMapping
    public ResponseEntity<PageDetailsResponse<List<QuizResponse>>> getQuizzesWithFilter(Pageable pageable, @Filter Specification<QuizEntity> specification) {
        return ResponseEntity.ok(quizService.getQuizzesWithFilter(pageable, specification));
    }

    @ApiMessage("Lấy tất cả bài kiểm tra của 1 expert thành công!")
    @GetMapping("/expert")
    public ResponseEntity<List<ChapterResponse.QuizInfoResponse>> getAllQuizzesByExpert() {
        return ResponseEntity.ok(quizService.getAllQuizzesByExpert());
    }

    @ApiMessage("Xóa bài kiểm tra thành công!")
    @DeleteMapping("/{quizId}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable Long quizId) {
        quizService.deleteQuiz(quizId);
        return ResponseEntity.ok().build();
    }
}
