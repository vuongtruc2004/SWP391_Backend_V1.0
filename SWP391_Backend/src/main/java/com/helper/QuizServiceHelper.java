package com.helper;

import com.dto.request.QuizAttemptRequest;
import com.dto.response.QuizAttemptResponse;
import com.entity.AnswerEntity;
import com.entity.QuestionEntity;
import com.entity.QuizAttemptEntity;
import com.entity.UserAnswerEntity;
import com.exception.custom.NotFoundException;
import com.repository.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class QuizServiceHelper {

    private final ModelMapper modelMapper;
    private final QuizAttemptRepository quizAttemptRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final UserAnswerRepository userAnswerRepository;
    private final QuizRepository quizRepository;

    public QuizAttemptResponse convertToQuizAttemptResponse(QuizAttemptEntity quizAttemptEntity) {
        QuizAttemptResponse response = modelMapper.map(quizAttemptEntity, QuizAttemptResponse.class);
        if (quizAttemptEntity.getUserAnswers() != null) {
            List<QuizAttemptResponse.UserAnswerResponse> userAnswers = quizAttemptEntity.getUserAnswers().stream()
                    .map(userAnswerEntity -> QuizAttemptResponse.UserAnswerResponse.builder()
                            .questionId(userAnswerEntity.getQuestion().getQuestionId())
                            .answerIds(userAnswerEntity.getAnswers().stream().map(AnswerEntity::getAnswerId).toList())
                            .build()).toList();
            response.setUserAnswers(userAnswers);
        }
        return response;
    }

    public QuizAttemptEntity saveQuizAttemptEntity(QuizAttemptRequest quizAttemptRequest) {
        QuizAttemptEntity quizAttempt = quizAttemptRepository.findById(quizAttemptRequest.getQuizAttemptId())
                .orElseThrow(() -> new NotFoundException("Quiz attempt không tồn tại!"));

        Set<UserAnswerEntity> oldUserAnswers = userAnswerRepository.findAllByQuizAttempt_QuizAttemptId(quizAttempt.getQuizAttemptId());
        if (!oldUserAnswers.isEmpty()) {
            userAnswerRepository.deleteAll(oldUserAnswers);
        }

        Set<UserAnswerEntity> userAnswerEntities = quizAttemptRequest.getUserAnswers().stream()
                .map(userAnswerRequest -> {
                    Set<AnswerEntity> answerEntities = userAnswerRequest.getAnswerIds().stream()
                            .map(answerId -> answerRepository.findById(answerId)
                                    .orElseThrow(() -> new NotFoundException("Câu trả lời không tồn tại!")))
                            .collect(Collectors.toSet());

                    QuestionEntity question = questionRepository.findById(userAnswerRequest.getQuestionId())
                            .orElseThrow(() -> new NotFoundException("Không tìm thấy câu hỏi!"));

                    return UserAnswerEntity.builder()
                            .question(question)
                            .answers(answerEntities)
                            .quizAttempt(quizAttempt)
                            .build();
                })
                .collect(Collectors.toSet());

        quizAttempt.setUserAnswers(userAnswerEntities);
        return quizAttemptRepository.save(quizAttempt);
    }

    public int calculateNumberOfCorrects(QuizAttemptEntity quizAttemptEntity) {
        int numberOfCorrectQuestions = 0;

        for (UserAnswerEntity userAnswerEntity : quizAttemptEntity.getUserAnswers()) {
            QuestionEntity question = userAnswerEntity.getQuestion();
            long realCorrectAnswers = question.getAnswers().stream()
                    .filter(AnswerEntity::getCorrect)
                    .count();

            Set<AnswerEntity> answers = userAnswerEntity.getAnswers();
            boolean isCorrect = answers.size() == realCorrectAnswers
                    && answers.stream().allMatch(AnswerEntity::getCorrect);

            if (isCorrect) {
                numberOfCorrectQuestions++;
            }
        }

        return numberOfCorrectQuestions;
    }

}
