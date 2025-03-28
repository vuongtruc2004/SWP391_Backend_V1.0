package com.service;

import com.dto.request.QuestionRequest;
import com.dto.request.QuizRequest;
import com.dto.response.ChapterResponse;
import com.dto.response.PageDetailsResponse;
import com.dto.response.QuizResponse;
import com.entity.*;
import com.exception.custom.*;
import com.helper.UserServiceHelper;
import com.repository.ChapterRepository;
import com.repository.QuestionRepository;
import com.repository.QuizRepository;
import com.util.BuildResponse;
import com.util.enums.RoleNameEnum;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizService {
    private final QuizRepository quizRepository;
    private final ModelMapper modelMapper;
    private final ChapterRepository chapterRepository;
    private final QuestionRepository questionRepository;
    private final UserServiceHelper userServiceHelper;

    public boolean published(Long id) {
        QuizEntity quizEntity = quizRepository.findById(id).
                orElseThrow(() -> new NotFoundException("Không tìm thấy bài kiểm tra"));
        if (Boolean.TRUE.equals(quizEntity.getPublished())) {
            quizEntity.setPublished(false);
        } else {
            quizEntity.setPublished(true);
        }
        return quizRepository.save(quizEntity).getPublished();
    }

    public QuizResponse createQuiz(QuizRequest quizRequest) {
        UserEntity userEntity = userServiceHelper.extractUserFromToken();
        if (userEntity == null || userEntity.getExpert() == null || userEntity.getRole().getRoleName() != RoleNameEnum.EXPERT) {
            throw new NotFoundException("Người dùng không hợp lệ");
        }

        if (quizRequest.getQuizId() != null) {
            throw new InvalidRequestInput("Tạo không được truyền vào quizId");

        }
        boolean checked = quizRepository.existsByTitle(quizRequest.getTitle());
        if (checked) {
            throw new TitleQuizException("Tiêu đề bài kiểm tra đã tồn tại!");
        }
        ChapterEntity chapterEntity = chapterRepository.findById(quizRequest.getChapterId())
                .orElseThrow(() -> new NotFoundException("ChapterId không tồn tại"));
        if (chapterEntity.getQuizz() != null) {
            throw new ChapterException("Chương này đã có bài kiểm tra");
        }

        List<QuestionEntity> questionEntityList = new ArrayList<>();

        for (Long id : quizRequest.getBankQuestionIds()) {
            QuestionEntity questionEntity = questionRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("QuestionId không tồn tại"));
            questionEntityList.add(questionEntity);
        }

        for (QuestionRequest questionRequest : quizRequest.getNewQuestions()) {
            QuestionEntity questionEntity = new QuestionEntity();
            List<AnswerEntity> set = new ArrayList<>();
            for (QuestionRequest.AnswerRequest answerRequest : questionRequest.getAnswers()) {
                AnswerEntity answerEntity = AnswerEntity.builder()
                        .content(answerRequest.getContent())
                        .correct(answerRequest.getCorrect())
                        .build();
                answerEntity.setQuestion(questionEntity);
                set.add(answerEntity);
            }
            questionEntity.setTitle(questionRequest.getTitle());
            questionEntity.setAnswers(set);
            questionEntityList.add(questionRepository.save(questionEntity));

        }

        QuizEntity quizEntity = modelMapper.map(quizRequest, QuizEntity.class);
        quizEntity.setChapter(chapterEntity);
        quizEntity.setQuestions(questionEntityList);
        quizEntity.setExpert(userEntity.getExpert());

        return modelMapper.map(quizRepository.save(quizEntity), QuizResponse.class);
    }

    public QuizResponse updateQuiz(QuizRequest quizRequest) {
        UserEntity userEntity = userServiceHelper.extractUserFromToken();
        if (userEntity == null || userEntity.getExpert() == null || userEntity.getRole().getRoleName() != RoleNameEnum.EXPERT) {
            throw new NotFoundException("Người dùng không hợp lệ");

        }

        if (quizRequest.getQuizId() == null) {
            throw new InvalidRequestInput("Cập nhật cần có quizId");
        }

        QuizEntity quizEntity = quizRepository.findById(quizRequest.getQuizId())
                .orElseThrow(() -> new NotFoundException("quizId không tồn tại!"));

        if (!quizEntity.getTitle().equals(quizRequest.getTitle()) && quizRepository.existsByTitle(quizRequest.getTitle())) {
            throw new TitleQuizException("Tiêu đề bài kiểm tra đã tồn tại!");
        }
        ChapterEntity chapterEntity = chapterRepository.findById(quizRequest.getChapterId())
                .orElseThrow(() -> new NotFoundException("ChapterId không tồn tại"));

        List<QuestionEntity> questionEntityList = new ArrayList<>();

        for (Long id : quizRequest.getBankQuestionIds()) {
            QuestionEntity questionEntity = questionRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("QuestionId không tồn tại"));
            questionEntityList.add(questionEntity);
        }

        for (QuestionRequest questionRequest : quizRequest.getNewQuestions()) {

            QuestionEntity questionEntity = new QuestionEntity();
            List<AnswerEntity> set = new ArrayList<>();
            for (QuestionRequest.AnswerRequest answerRequest : questionRequest.getAnswers()) {
                AnswerEntity answerEntity = AnswerEntity.builder()
                        .content(answerRequest.getContent())
                        .correct(answerRequest.getCorrect())
                        .build();
                answerEntity.setQuestion(questionEntity);
                set.add(answerEntity);
            }
            questionEntity.setTitle(questionRequest.getTitle());
            questionEntity.setAnswers(set);
            questionEntityList.add(questionRepository.save(questionEntity));
        }
        quizEntity.setTitle(quizRequest.getTitle());
        quizEntity.setDescription(quizRequest.getDescription());
        quizEntity.setChapter(chapterEntity);
        quizEntity.setPublished(quizRequest.getPublished());
        quizEntity.setDuration(quizRequest.getDuration());
        quizEntity.setAllowSeeAnswers(quizRequest.getAllowSeeAnswers());
        quizEntity.setQuestions(questionEntityList);
        quizEntity.setUpdatedAt(Instant.now());
        return modelMapper.map(quizRepository.save(quizEntity), QuizResponse.class);

    }

    public QuizResponse getQuizByQuizId(Long quizId) {
        QuizEntity quizEntity = quizRepository.findById(quizId)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy bài kiểm tra!"));
        return modelMapper.map(quizEntity, QuizResponse.class);
    }

    public PageDetailsResponse<List<QuizResponse>> getQuizzesWithFilter(Pageable pageable, Specification<QuizEntity> specification) {
        UserEntity userEntity = userServiceHelper.extractUserFromToken();
        if (userEntity == null) {
            throw new UserException("Vui lòng đăng nhập!");
        }
        Page<QuizEntity> page;
        if (userEntity.getRole().getRoleName() == RoleNameEnum.EXPERT) {
            if (userEntity.getExpert() == null) {
                throw new UserException("Tài khoản không phải Exert");
            }

            specification = specification.and(((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("expert"), userEntity.getExpert())));
            page = quizRepository.findAll(specification, pageable);
        } else if (userEntity.getRole().getRoleName() == RoleNameEnum.ADMIN) {
            page = quizRepository.findAll(specification, pageable);
        } else {
            throw new UserException("Tài khoản không được truy cập");
        }
        List<QuizResponse> quizResponseList = page.getContent().stream()
                .map(quizEntity -> {
                    return modelMapper.map(quizEntity, QuizResponse.class);
                }).toList();
        return BuildResponse.buildPageDetailsResponse(
                page.getNumber() + 1,
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements(),
                quizResponseList
        );
    }

    public List<ChapterResponse.QuizInfoResponse> getAllQuizzesByExpert() {
        UserEntity userEntity = userServiceHelper.extractUserFromToken();
        if (userEntity == null || userEntity.getRole().getRoleName() != RoleNameEnum.EXPERT || userEntity.getExpert() == null) {
            throw new UserException("Vui lòng đăng nhập bằng tài khoản EXPERT để thực hiện chức năng này!");
        }
        return userEntity.getExpert().getQuizzes().stream()
                .map(quizEntity -> modelMapper.map(quizEntity, ChapterResponse.QuizInfoResponse.class))
                .sorted((q1, q2) -> q2.getUpdatedAt().compareTo(q1.getUpdatedAt()))
                .toList();
    }

}
