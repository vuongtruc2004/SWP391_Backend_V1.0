package com.service;

import com.dto.response.*;
import com.entity.AnswerEntity;
import com.entity.QuizEntity;
import com.entity.UserEntity;
import com.exception.custom.NotFoundException;
import com.repository.QuizRepository;
import com.util.BuildResponse;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class QuizService {
    private final QuizRepository quizRepository;
    private final ModelMapper modelMapper;

    public QuizService(QuizRepository quizRepository, ModelMapper modelMapper) {
        this.quizRepository = quizRepository;
        this.modelMapper = modelMapper;
    }

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
        if(quizEntity.getPublished()) {
            quizEntity.setPublished(false);
        }else{
            quizEntity.setPublished(true);
        }
        return quizRepository.save(quizEntity).getPublished();
    }


}
