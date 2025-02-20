package com.controller.api.v1;

import com.dto.request.AnswerRequest;
import com.dto.response.*;
import com.entity.QuizEntity;
import com.entity.UserEntity;
import com.service.AnswerService;
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


}
