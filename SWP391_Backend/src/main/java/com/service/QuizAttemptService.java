package com.service;

import com.dto.request.QuizAttemptRequest;
import com.dto.response.QuizAttemptResponse;
import com.entity.*;
import com.repository.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuizAttemptService {
    private final QuizAttemptRepository quizAttemptRepository;
    private final UserRepository userRepository;
    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final UserAnswerRepository userAnswerRepository;
    private final ModelMapper modelMapper;
    private final Logger log = LoggerFactory.getLogger(QuizAttemptService.class);


    @Transactional
    public QuizAttemptResponse saveQuizAttempt(QuizAttemptRequest quizAttemptRequest, boolean isSubmit) {
        UserEntity user = userRepository.findById(quizAttemptRequest.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy người dùng"));
        QuizEntity quiz = quizRepository.findById(quizAttemptRequest.getQuizId())
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy bài kiểm tra"));

        QuizAttemptEntity quizAttempt;

        if (quizAttemptRequest.getQuizAttemptId() != null) {
            // 🔹 Nếu có quizAttemptId => Lấy bài làm cũ
            quizAttempt = quizAttemptRepository.findById(quizAttemptRequest.getQuizAttemptId())
                    .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy bài làm!"));
        } else {
            // 🔹 Nếu không có quizAttemptId => Tạo bài làm mới khi mở bài
            quizAttempt = new QuizAttemptEntity();
            quizAttempt.setUser(user);
            quizAttempt.setQuiz(quiz);
            quizAttempt.setStartTime(Instant.now());
            quizAttempt.setAttemptNumber(quizAttemptRepository.countByUserAndQuiz(user, quiz) + 1);
            quizAttempt = quizAttemptRepository.save(quizAttempt);
        }

        // 🔹 Lấy danh sách câu trả lời đã lưu trước đó (nếu có)
        Map<Long, List<UserAnswerEntity>> existingAnswers = userAnswerRepository.findByQuizAttempt(quizAttempt)
                .stream()
                .collect(Collectors.groupingBy(ua -> ua.getQuestion().getQuestionId()));

        Set<UserAnswerEntity> userAnswerEntities = new HashSet<>();

        for (QuizAttemptRequest.UserAnswerRequest answerRequest : quizAttemptRequest.getUserAnswers()) {
            QuestionEntity question = questionRepository.findById(answerRequest.getQuestionId())
                    .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy câu hỏi"));

            // 🔹 Nếu câu hỏi đã có câu trả lời trước đó, lấy danh sách cũ
            List<UserAnswerEntity> existingUserAnswers = existingAnswers.getOrDefault(question.getQuestionId(), new ArrayList<>());

            // 🔹 Chuyển danh sách answerId thành Set để so sánh nhanh
            Set<Long> newAnswerIds = new HashSet<>(answerRequest.getAnswerIds());
            Set<Long> existingAnswerIds = existingUserAnswers.stream()
                    .map(ua -> ua.getAnswer().getAnswerId())
                    .collect(Collectors.toSet());

            if (!newAnswerIds.equals(existingAnswerIds)) {
                // 🔹 Xóa các câu trả lời cũ nếu có sự thay đổi
                if (!existingUserAnswers.isEmpty()) {
                    userAnswerRepository.deleteAll(existingUserAnswers);
                }

                // 🔹 Lưu câu trả lời mới
                for (Long answerId : newAnswerIds) {
                    AnswerEntity answer = answerRepository.findById(answerId)
                            .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy đáp án"));

                    UserAnswerEntity userAnswer = new UserAnswerEntity();
                    userAnswer.setQuizAttempt(quizAttempt);
                    userAnswer.setQuestion(question);
                    userAnswer.setAnswer(answer);
                    userAnswerEntities.add(userAnswer);
                }
            } else {
                // 🔹 Nếu câu trả lời không thay đổi, giữ nguyên
                userAnswerEntities.addAll(existingUserAnswers);
            }
        }

        // 🔹 Luôn lưu lại câu trả lời mới nếu có sự thay đổi
        if (!userAnswerEntities.isEmpty()) {
            userAnswerRepository.saveAll(userAnswerEntities);
        }

        // 🔹 Khi nộp bài, tính điểm và cập nhật thời gian kết thúc
        if (isSubmit) {
            double score = calculateScore(quiz, userAnswerEntities);
            quizAttempt.setScore(score);
            quizAttempt.setEndTime(Instant.now());
        }

        quizAttempt.setUserAnswers(userAnswerEntities);
        quizAttempt = quizAttemptRepository.save(quizAttempt);

        // 🔹 Trả về QuizAttemptResponse
        QuizAttemptResponse response = modelMapper.map(quizAttempt, QuizAttemptResponse.class);
        response.setUserAnswers(userAnswerEntities.stream()
                .collect(Collectors.groupingBy(UserAnswerEntity::getQuestion))
                .entrySet().stream()
                .map(entry -> new QuizAttemptResponse.UserAnswerResponse(
                        entry.getKey().getQuestionId(),
                        entry.getValue().stream()
                                .map(userAnswer -> userAnswer.getAnswer().getAnswerId())
                                .collect(Collectors.toList())
                ))
                .collect(Collectors.toList()));

        return response;
    }




    private double calculateScore(QuizEntity quiz, Set<UserAnswerEntity> userAnswers) {
        double totalScore = 0;
        int totalQuestions = quiz.getQuestions().size();

        for (QuestionEntity question : quiz.getQuestions()) {
            List<Long> correctAnswerIds = question.getAnswers().stream()
                    .filter(AnswerEntity::getCorrect)
                    .map(AnswerEntity::getAnswerId)
                    .collect(Collectors.toList());

            List<Long> userAnswerIds = userAnswers.stream()
                    .filter(userAnswer -> userAnswer.getQuestion().equals(question))
                    .map(userAnswer -> userAnswer.getAnswer().getAnswerId())
                    .collect(Collectors.toList());


            // 🔹 Kiểm tra nếu người dùng chọn đúng tất cả đáp án
            if (new HashSet<>(userAnswerIds).equals(new HashSet<>(correctAnswerIds))) {
                totalScore += 1;
            }
        }

        System.out.println("Total Score (Before Percentage): " + totalScore);
        System.out.println("Total Questions: " + totalQuestions);

        return totalQuestions > 0 ? (totalScore / totalQuestions) * 10 : 0;
    }


    public QuizAttemptResponse getQuizAttemptByUserAndQuiz(Long userId, Long quizId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        QuizEntity quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new EntityNotFoundException("Quiz not found"));

        // 🔹 Lấy danh sách bài làm
        List<QuizAttemptEntity> attempts = quizAttemptRepository.findByUserAndQuiz(user, quiz);

        // 🔹 Lấy bài làm mới nhất (nếu có)
        QuizAttemptEntity quizAttempt = attempts.isEmpty()
                ? null
                : attempts.get(0);

        if (quizAttempt == null) {
            throw new EntityNotFoundException("No quiz attempt found");
        }


        // Ánh xạ sang QuizAttemptResponse
        QuizAttemptResponse response = modelMapper.map(quizAttempt, QuizAttemptResponse.class);
        response.setUserAnswers(quizAttempt.getUserAnswers().stream()
                .collect(Collectors.groupingBy(UserAnswerEntity::getQuestion))
                .entrySet().stream()
                .map(entry -> new QuizAttemptResponse.UserAnswerResponse(
                        entry.getKey().getQuestionId(),
                        entry.getValue().stream()
                                .map(userAnswer -> userAnswer.getAnswer().getAnswerId())
                                .collect(Collectors.toList())
                ))
                .collect(Collectors.toList()));

        return response;
    }


}
