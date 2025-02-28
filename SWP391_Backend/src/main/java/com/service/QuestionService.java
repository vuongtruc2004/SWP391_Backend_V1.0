package com.service;


import com.dto.request.QuestionRequest;
import com.dto.request.QuizRequest;
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
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;
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
        List<QuestionEntity> allQuestions = questionRepository.findAll(specification);
        List<QuestionResponse> questionResponses = questionServiceHelper.convertToListResponse(new PageImpl<>(allQuestions));
        questionResponses = questionResponses.stream()
                .filter(Objects::nonNull)
                .sorted(Comparator.comparing(
                        q -> Optional.ofNullable(q.getLatestUpdate()).orElse(Instant.MIN),
                        Comparator.reverseOrder()
                ))
                .toList();
        Long totalElements = (long) questionResponses.size();
        int pageNumber = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        int fromIndex = pageNumber * pageSize;
        int toIndex = (int) Math.min(fromIndex + pageSize, totalElements);
        List<QuestionResponse> pagedResponses = (fromIndex >= totalElements) ? Collections.emptyList() :
                questionResponses.subList(fromIndex, toIndex);

        return BuildResponse.buildPageDetailsResponse(
                pageNumber + 1,
                pageSize,
                (int) Math.ceil((double) totalElements / pageSize),
                totalElements,
                pagedResponses
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

    public Set<QuestionEntity> saveQuestionWithQuiz(QuizRequest quizRequest) throws Exception {
        Set<QuestionEntity> questionEntitySet = new HashSet<>();
        for (String question : quizRequest.getQuestions()) {
            boolean checkExistsQuestion = questionRepository.existsByTitle(question.trim());
            if (checkExistsQuestion) {
                QuestionEntity questionEntity = questionRepository.findByTitle(question.trim());
                questionEntitySet.add(questionEntity);
            } else {
                throw new NotFoundException("Câu hỏi chưa tồn tại");
            }
        }
        return questionEntitySet;
    }

    public List<QuestionResponse> getAllQuestions() {
        List<QuestionEntity> questionEntityList = this.questionRepository.findAll();

        return questionEntityList.stream()
                .map(questionEntity -> {
                    List<String> correctAnswers = questionEntity.getAnswers().stream()
                            .filter(AnswerEntity::getCorrect)
                            .map(AnswerEntity::getContent)
                            .collect(Collectors.toList());

                    return QuestionResponse.builder()
                            .questionId(questionEntity.getQuestionId())
                            .title(questionEntity.getTitle())
                            .correctAnswer(correctAnswers)
                            .build();
                })
                .toList();
    }

}
