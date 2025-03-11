package com.service;

import com.dto.request.QuestionRequest;
import com.dto.request.QuizRequest;
import com.dto.response.QuizResponse;
import com.entity.*;
import com.exception.custom.InvalidRequestInput;
import com.exception.custom.NotFoundException;
import com.helper.UserServiceHelper;
import com.repository.ChapterRepository;
import com.repository.QuestionRepository;
import com.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class QuizService {
    private final QuizRepository quizRepository;
    private final ModelMapper modelMapper;
    private final QuestionService questionService;
    private final ChapterRepository chapterRepository;
    private final QuestionRepository questionRepository;
    private final UserServiceHelper userServiceHelper;

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

    public QuizResponse createQuiz(QuizRequest quizRequest) {
        UserEntity userEntity = userServiceHelper.extractUserFromToken();
        if (userEntity == null || userEntity.getExpert() == null) {
            throw new NotFoundException("Người dùng không hợp lệ");

        }

        if (quizRequest.getQuizId() != null) {
            throw new InvalidRequestInput("Tạo không được truyền vào quizId");

        }
        ChapterEntity chapterEntity = chapterRepository.findByExpertIdAndChapterId(userEntity.getExpert().getExpertId(), quizRequest.getChapterId())
                .orElseThrow(() -> new NotFoundException("ChapterId không tồn tại"));
        if (chapterEntity.getQuizz() != null) {
            throw new InvalidRequestInput("Chương này đã có bài kiểm tra");
        }

        Set<QuestionEntity> questionEntityList = new HashSet<>();

        for (Long id : quizRequest.getBankQuestionIds()) {
            QuestionEntity questionEntity = questionRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("QuestionId không tồn tại"));
            questionEntityList.add(questionEntity);
        }

        for (QuestionRequest questionRequest : quizRequest.getNewQuestions()) {
            if (questionRepository.existsByTitle(questionRequest.getTitle())) {
                throw new InvalidRequestInput("Câu hỏi đã tồn tại");
            }
            Set<AnswerEntity> set = new HashSet<>();
            for (QuestionRequest.AnswerRequest answerRequest : questionRequest.getAnswers()) {
                set.add(AnswerEntity.builder()
                        .content(answerRequest.getContent())
                        .correct(answerRequest.getCorrect())
                        .build());
            }

            questionEntityList.add(questionRepository.save(QuestionEntity.builder()
                    .title(questionRequest.getTitle())
                    .answers(set)
                    .build()));
        }

        QuizEntity quizEntity = modelMapper.map(quizRequest, QuizEntity.class);
        quizEntity.setChapter(chapterEntity);
        quizEntity.setQuestions(questionEntityList);

        return modelMapper.map(quizRepository.save(quizEntity), QuizResponse.class);

    }

    public QuizResponse getQuizByQuizId(Long quizId) {
        QuizEntity quizEntity = quizRepository.findById(quizId)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy bài kiểm tra!"));
        return modelMapper.map(quizEntity, QuizResponse.class);
    }

}
