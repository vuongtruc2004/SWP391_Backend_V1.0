package com.controller.api.v1;

import com.dto.response.CourseResponse;
import com.dto.response.PageDetailsResponse;
import com.dto.response.QuestionResponse;
import com.entity.CourseEntity;
import com.entity.QuestionEntity;
import com.repository.QuestionRepository;
import com.service.QuestionService;
import com.turkraft.springfilter.boot.Filter;
import com.util.annotation.ApiMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/v1/questions")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    @ApiMessage("Lấy các câu hỏi thành công")
    @GetMapping
    public ResponseEntity<PageDetailsResponse<List<QuestionResponse>>> getQuestionsWithFilter(
            @Filter Specification<QuestionEntity> specification,
            Pageable pageable,
            @RequestParam(name = "title", required = false) String title
    ) {
        return ResponseEntity.ok(this.questionService.getQuestionWithFilter(specification, pageable, title));
    }
}
