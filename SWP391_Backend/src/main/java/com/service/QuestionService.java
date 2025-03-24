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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class QuestionService {
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    private final QuestionServiceHelper questionServiceHelper;
    private final QuizRepository quizRepository;
    private final ModelMapper modelMapper;


    public PageDetailsResponse<List<QuestionResponse>> getAllQuestionWithFilter(Pageable pageable, Specification<QuestionEntity> specification) {
        Page<QuestionEntity> page = questionRepository.findAll(specification, pageable);
        List<QuestionResponse> listQuestionResponse = page
                .getContent()
                .stream()
                .map(entity -> modelMapper.map(entity, QuestionResponse.class))
                .toList();
        return BuildResponse.buildPageDetailsResponse(
                page.getNumber() + 1,
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements(),
                listQuestionResponse
        );
    }


    @Transactional
    public ApiResponse<QuestionResponse> createQuestion(QuestionRequest request) {
        boolean existTitle = questionRepository.existsByTitle(request.getTitle().trim());
        if (existTitle) {
            throw new QuestionException("Câu hỏi đã tồn tại!");
        }
        QuestionEntity question = new QuestionEntity();
        question.setTitle(request.getTitle());
        List<AnswerEntity> answers = request.getAnswers()
                .stream()
                .map(answerDto -> {
                    AnswerEntity answerEntity = modelMapper.map(answerDto, AnswerEntity.class);
                    answerEntity.setQuestion(question); // ✅ Ensure answers reference the question
                    return answerEntity;
                })
                .toList();
        question.setAnswers(answers);
        questionRepository.save(question);
        return BuildResponse.buildApiResponse(
                HttpStatus.CREATED.value(),
                "Tạo câu hỏi thành công",
                null,
                modelMapper.map(question, QuestionResponse.class)
        );
    }

    @Transactional
    public ApiResponse<QuestionResponse> updateQuestion(Long questionId, QuestionRequest request) {
        QuestionEntity question = questionRepository.findById(questionId)
                .orElseThrow(() -> new QuestionException("Câu hỏi không tồn tại!"));
        question.setTitle(request.getTitle());
        answerRepository.deleteAll(question.getAnswers());
        question.getAnswers().clear();
        List<AnswerEntity> newAnswers = request.getAnswers().stream()
                .map(answer -> {
                    AnswerEntity answerEntity = modelMapper.map(answer, AnswerEntity.class);
                    answerEntity.setQuestion(question);
                    return answerEntity;
                })
                .toList();
        question.getAnswers().addAll(newAnswers);

        questionRepository.save(question);

        return BuildResponse.buildApiResponse(
                HttpStatus.OK.value(),
                "Cập nhật câu hỏi thành công",
                null,
                modelMapper.map(question, QuestionResponse.class)
        );
    }

    public void deleteQuestion(Long questionId) {
        if (!questionRepository.existsById(questionId)) {
            throw new NotFoundException("Không tìm thấy câu hỏi để xóa!");
        }
        questionRepository.deleteById(questionId);
    }



    public PageDetailsResponse<List<QuestionResponse>> getAllQuestions(Specification<QuestionEntity> specification, Pageable pageable) {
        Page<QuestionEntity> page = questionRepository.findAll(specification, pageable);
        List<QuestionResponse> questionResponses = page.getContent().stream()
                .map(questionEntity -> modelMapper.map(questionEntity, QuestionResponse.class)).toList();
        return BuildResponse.buildPageDetailsResponse(
                page.getNumber() + 1,
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements(),
                questionResponses
        );

    }

}
