package com.service;

import com.dto.request.QuizAttemptRequest;
import com.dto.response.QuizAttemptResponse;
import com.entity.QuizAttemptEntity;
import com.entity.QuizEntity;
import com.entity.UserEntity;
import com.exception.custom.NotFoundException;
import com.exception.custom.UserException;
import com.helper.QuizServiceHelper;
import com.helper.UserServiceHelper;
import com.repository.QuizAttemptRepository;
import com.repository.QuizRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizAttemptService {
    private final QuizAttemptRepository quizAttemptRepository;
    private final QuizRepository quizRepository;
    private final UserServiceHelper userServiceHelper;
    private final QuizServiceHelper quizServiceHelper;
    private final ModelMapper modelMapper;

    public QuizAttemptResponse createNewQuizAttempt(Long quizId) {
        UserEntity user = userServiceHelper.extractUserFromToken();
        if (user == null) {
            throw new UserException("Vui lòng đăng nhập để thực hiện chức năng này!");
        }
        QuizEntity quizEntity = quizRepository.findByQuizIdAndPublishedTrue(quizId)
                .orElseThrow(() -> new NotFoundException("Quiz không tồn tại!"));

        QuizAttemptEntity quizAttempt = quizAttemptRepository.findTopByUserAndQuizOrderByStartTimeDesc(user, quizEntity).orElse(null);
        if (quizAttempt != null && quizAttempt.getEndTime() == null && !quizAttempt.getStartTime().plus(quizEntity.getDuration(), ChronoUnit.SECONDS).isBefore(Instant.now())) {
            return quizServiceHelper.convertToQuizAttemptResponse(quizAttempt);
        }

        QuizAttemptEntity newQuizAttempt = QuizAttemptEntity.builder()
                .quiz(quizEntity)
                .user(user)
                .attemptNumber(quizAttemptRepository.countByUserAndQuiz(user, quizEntity) + 1)
                .build();

        return quizServiceHelper.convertToQuizAttemptResponse(quizAttemptRepository.save(newQuizAttempt));
    }

    @Transactional
    public QuizAttemptResponse saveQuizAttempt(QuizAttemptRequest quizAttemptRequest) {
        QuizAttemptEntity newQuizAttempt = quizServiceHelper.saveQuizAttemptEntity(quizAttemptRequest);
        return quizServiceHelper.convertToQuizAttemptResponse(quizAttemptRepository.save(newQuizAttempt));
    }

    public QuizAttemptResponse submitQuizAttempt(QuizAttemptRequest quizAttemptRequest) {
        QuizAttemptEntity newQuizAttempt = quizServiceHelper.saveQuizAttemptEntity(quizAttemptRequest);
        newQuizAttempt.setEndTime(Instant.now());
        newQuizAttempt.setNumberOfCorrects(quizServiceHelper.calculateNumberOfCorrects(newQuizAttempt));
        return quizServiceHelper.convertToQuizAttemptResponse(quizAttemptRepository.save(newQuizAttempt));
    }

    public List<QuizAttemptResponse> getAllQuizAttemptsByUserAndQuiz(Long quizId) {
        UserEntity user = userServiceHelper.extractUserFromToken();
        if (user == null) {
            throw new UserException("Vui lòng đăng nhập để thực hiện chức năng này!");
        }
        QuizEntity quiz = quizRepository.findByQuizIdAndPublishedTrue(quizId)
                .orElseThrow(() -> new NotFoundException("Bài kiểm tra không tồn tại!"));
        return quizAttemptRepository.findAllByUserAndQuiz(user, quiz).stream()
                .map(quizServiceHelper::convertToQuizAttemptResponse)
                .toList();
    }
}
