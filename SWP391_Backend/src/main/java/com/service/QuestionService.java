package com.service;


import com.dto.request.QuestionRequest;
import com.dto.response.ApiResponse;
import com.dto.response.PageDetailsResponse;
import com.dto.response.QuestionResponse;
import com.entity.AnswerEntity;
import com.entity.QuestionEntity;
import com.exception.custom.NotFoundException;
import com.exception.custom.QuestionException;
import com.helper.QuestionServiceHelper;
import com.repository.AnswerRepository;
import com.repository.QuestionRepository;
import com.repository.QuizRepository;
import com.util.BuildResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class QuestionService {
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    private final QuestionServiceHelper questionServiceHelper;
    private final QuizRepository quizRepository;
    private final ModelMapper modelMapper;

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


    public ApiResponse<QuestionResponse> createQuestion(QuestionRequest request) {
        QuestionEntity questionEntityOptional = new QuestionEntity();
        boolean existTitle = questionRepository.existsByTitle(request.getTitle().trim());
        if(existTitle) {
            throw new QuestionException("Câu hỏi đã tồn tại!");
        }
        questionEntityOptional.setTitle(request.getTitle());
        Set<AnswerEntity> answerEntities = request.getAnswersId().stream()
                .map(answerId -> answerRepository.findById(answerId)
                        .orElseThrow(() -> new NotFoundException("Không tìm thấy đáp án với ID: " + answerId)))
                .collect(Collectors.toSet());

        questionEntityOptional.setAnswers(answerEntities);

        for (AnswerEntity answer : answerEntities) {
            answer.setQuestion(questionEntityOptional);
        }

        questionEntityOptional = questionRepository.save(questionEntityOptional);

        // Xử lý danh sách đáp án đúng ngay trong createQuestion
        List<String> correctAnswers = answerEntities.stream()
                .filter(AnswerEntity::getCorrect) // Lọc ra đáp án đúng
                .map(AnswerEntity::getContent)   // Lấy nội dung của đáp án
                .collect(Collectors.toList());

        // Tạo QuestionResponse mà không cần constructor đặc biệt
        QuestionResponse questionResponse = new QuestionResponse();
        questionResponse.setQuestionId(questionEntityOptional.getQuestionId());
        questionResponse.setTitle(questionEntityOptional.getTitle());
        questionResponse.setCorrectAnswer(correctAnswers);

        return BuildResponse.buildApiResponse(
                HttpStatus.CREATED.value(),
                "Tạo câu hỏi thành công",
                null,
                questionResponse
        );
    }

    public ApiResponse<QuestionResponse> updateQuestion(Long questionId, QuestionRequest questionRequest) {
        Optional<QuestionEntity> questionEntityOptional=this.questionRepository.findById(questionId);
        QuestionEntity questionEntity = null;
        if(questionEntityOptional.isPresent()) {
            questionEntity = questionEntityOptional.get();
            questionEntity.setTitle(questionRequest.getTitle());
            questionRepository.save(questionEntity);
        } else {
            throw new NotFoundException("Không tìm thấy Id câu hỏi!");
        }
        return BuildResponse.buildApiResponse(
                HttpStatus.OK.value(),
                "Thay đổi thông tin môn học",
                null,
                modelMapper.map(questionEntity, QuestionResponse.class)
        );
    }


}
