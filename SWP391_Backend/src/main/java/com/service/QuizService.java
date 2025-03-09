package com.service;

import com.dto.response.PageDetailsResponse;
import com.dto.response.QuestionResponse;
import com.dto.response.QuizResponse;
import com.entity.AnswerEntity;
import com.entity.QuizEntity;
import com.exception.custom.NotFoundException;
import com.repository.QuizRepository;
import com.util.BuildResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuizService {
    private final QuizRepository quizRepository;
    private final ModelMapper modelMapper;
    private final QuestionService questionService;

    public PageDetailsResponse<List<QuizResponse>> getQuizWithFilter(
            Pageable pageable, Specification<QuizEntity> specification) {

        Page<QuizEntity> page = quizRepository.findAll(specification, pageable);
        List<QuizResponse> quizResponses = page.getContent()
                .stream()
                .map(quizEntity -> {
                    QuizResponse quizResponse = modelMapper.map(quizEntity, QuizResponse.class);

                    Set<QuestionResponse> questionResponses = quizEntity.getQuestions().stream()
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
                            .collect(Collectors.toSet());

                    quizResponse.setQuestions(questionResponses);
                    return quizResponse;
                })
                .toList();

        return BuildResponse.buildPageDetailsResponse(
                page.getNumber() + 1,
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements(),
                quizResponses
        );
    }

    public boolean published(Long id) {
        QuizEntity quizEntity = quizRepository.findById(id).
                orElseThrow(() -> new NotFoundException("Không tìm thấy bài kiểm tra"));
        if (quizEntity.getPublished()) {
            quizEntity.setPublished(false);
        } else {
            quizEntity.setPublished(true);
        }
        return quizRepository.save(quizEntity).getPublished();
    }

//    public QuizResponse createQuiz(QuizRequest quizRequest) throws Exception {
//        boolean check = quizRepository.existsByTitle(quizRequest.getTitle().trim());
//        if(!check) {
//            QuizEntity quizEntity = new QuizEntity();
//            quizEntity.setTitle(quizRequest.getTitle());
//            quizEntity.setPublished(quizRequest.getPublished());
//            if(quizRequest.getStartedAt() != null && quizRequest.getEndedAt() != null) {
//                quizEntity.setStartedAt(quizRequest.getStartedAt());
//                quizEntity.setEndedAt(quizRequest.getEndedAt());
//            }
//            quizEntity.setMaxAttempts(quizRequest.getMaxAttempts());
//            quizEntity.setCreatedAt(Instant.now());
//            Set<QuestionEntity> setQuestions = questionService.saveQuestionWithQuiz(quizRequest);
//            quizEntity.setQuestions(setQuestions);
//            return modelMapper.map(quizRepository.save(quizEntity), QuizResponse.class);
//        }else{
//            throw new InvalidRequestInput("Tiêu đề bài kiểm tra đã tồn tại");
//        }
//    }
//
//    public QuizResponse getQuiz(String title) {
//        QuizEntity quizEntity = quizRepository.findByTitle(title)
//                .orElseThrow(() ->  new NotFoundException("Không tìm thấy bài kiểm tra"));
//        return modelMapper.map(quizEntity, QuizResponse.class);
//    }
//
//    public QuizResponse updateQuiz(QuizRequest quizRequest) throws Exception {
//        QuizEntity quizEntity = quizRepository.findById(quizRequest.getQuizId())
//                .orElseThrow(() -> new NotFoundException("Không tìm thấy bài kiểm tra"));
//        quizEntity.setTitle(quizRequest.getTitle());
//        quizEntity.setPublished(quizRequest.getPublished());
//        if(quizRequest.getStartedAt() != null && quizRequest.getEndedAt() != null) {
//            quizEntity.setStartedAt(quizRequest.getStartedAt());
//            quizEntity.setEndedAt(quizRequest.getEndedAt());
//        }
//        quizEntity.setMaxAttempts(quizRequest.getMaxAttempts());
//        quizEntity.setUpdatedAt(Instant.now());
//        Set<QuestionEntity> setQuestions = questionService.saveQuestionWithQuiz(quizRequest);
//        quizEntity.setQuestions(setQuestions);
//        return modelMapper.map(quizRepository.save(quizEntity), QuizResponse.class);
//    }

}
