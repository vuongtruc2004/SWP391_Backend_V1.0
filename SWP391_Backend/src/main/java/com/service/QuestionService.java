package com.service;


import com.dto.response.CourseResponse;
import com.dto.response.PageDetailsResponse;
import com.dto.response.QuestionResponse;
import com.entity.CourseEntity;
import com.entity.QuestionEntity;
import com.entity.QuizEntity;
import com.helper.QuestionServiceHelper;
import com.repository.QuestionRepository;
import com.repository.QuizRepository;
import com.util.BuildResponse;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final QuestionServiceHelper questionServiceHelper;
    private final QuizRepository quizRepository;

    public PageDetailsResponse<List<QuestionResponse>> getQuestionWithFilter(
            Specification<QuestionEntity> specification,
            Pageable pageable,
            String title
    ) {

        if (title != null && !title.isEmpty()) {
            specification = specification.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.like(root.get("title"), "%" + title.trim() + "%"));
        }
            Page<QuestionEntity> page = questionRepository.findAll(specification, pageable);
            List<QuestionResponse> questionResponses = questionServiceHelper.convertToListResponse(page);
            return BuildResponse.buildPageDetailsResponse(
                    page.getNumber() + 1,
                    page.getSize(),
                    page.getTotalPages(),
                    page.getTotalElements(),
                    questionResponses
            );



    }

}
