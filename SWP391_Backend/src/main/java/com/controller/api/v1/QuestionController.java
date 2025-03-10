package com.controller.api.v1;

import com.dto.response.PageDetailsResponse;
import com.dto.response.QuestionResponse;
import com.entity.QuestionEntity;
import com.service.QuestionService;
import com.turkraft.springfilter.boot.Filter;
import com.util.annotation.ApiMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/questions")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

//    @ApiMessage("Lấy các câu hỏi thành công")
//    @GetMapping
//    public ResponseEntity<PageDetailsResponse<List<QuestionResponse>>> getQuestionsWithFilter(
//            @Filter Specification<QuestionEntity> specification,
//            Pageable pageable,
//            @RequestParam(name = "title", required = false) String title
//    ) {
//        return ResponseEntity.ok(this.questionService.getQuestionWithFilter(specification, pageable, title));
//    }

//    @ApiMessage("Tạo câu hỏi thành công!")
//    @PostMapping
//    public ResponseEntity<ApiResponse<QuestionResponse>> createQuestion(@RequestBody QuestionRequest questionRequest) {
//        return ResponseEntity.ok(questionService.createQuestion(questionRequest));
//    }
//
//    @ApiMessage("Thay đổi câu hỏi thành công!")
//    @PatchMapping("/update/{questionId}")
//    public ResponseEntity<ApiResponse<QuestionResponse>> updateQuestion(@PathVariable Long questionId, @RequestBody QuestionRequest questionRequest) {
//        return ResponseEntity.ok(questionService.updateQuestion(questionId, questionRequest));
//    }

    @ApiMessage("Lấy tất cả câu hỏi thành công")
    @GetMapping("/pagination")
    public ResponseEntity<PageDetailsResponse<List<QuestionResponse>>> getAllQuestions(@Filter Specification<QuestionEntity> specification, Pageable pageable) {
        return ResponseEntity.ok(questionService.getAllQuestions(specification, pageable));
    }


}
