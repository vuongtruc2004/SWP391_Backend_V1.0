package com.controller.api.v1;

import com.dto.response.PageDetailsResponse;
import com.dto.response.QuizResponse;
import com.entity.QuizEntity;
import com.service.QuizService;
import com.turkraft.springfilter.boot.Filter;
import com.util.annotation.ApiMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/quiz")
@RequiredArgsConstructor
public class QuizController {
    private final QuizService quizService;

    @ApiMessage("Lấy tất cả bài quiz thành công")
    @GetMapping
    public ResponseEntity<PageDetailsResponse<List<QuizResponse>>> getQuizWithFilter(
            Pageable pageable,
            @Filter Specification<QuizEntity> specification) {
        return ResponseEntity.ok(quizService.getQuizWithFilter(pageable, specification));
    }

    @ApiMessage("Mở bài quiz thành công")
    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> publishedQuiz(@PathVariable Long id) {
        return ResponseEntity.ok(quizService.published(id));
    }

//    @ApiMessage("Tạo bài kiểm tra thành công")
//    @PostMapping
//    public ResponseEntity<QuizResponse> createQuiz(@RequestBody QuizRequest quizRequest) throws Exception {
//        return ResponseEntity.status(HttpStatus.CREATED).body(quizService.createQuiz(quizRequest));
//    }
//
//    @ApiMessage("Lấy bài kiểm tra thanhf công")
//    @GetMapping("/{title}")
//    public ResponseEntity<QuizResponse> getQuiz(@PathVariable String title) {
//        return ResponseEntity.ok(quizService.getQuiz(title));
//    }
//
//    @ApiMessage("Cập nhật bài kiểm tra thành công")
//    @PutMapping
//    public ResponseEntity<QuizResponse> updateQuiz(@RequestBody QuizRequest quizRequest) throws Exception {
//        return ResponseEntity.ok(quizService.updateQuiz(quizRequest));
//    }


}
