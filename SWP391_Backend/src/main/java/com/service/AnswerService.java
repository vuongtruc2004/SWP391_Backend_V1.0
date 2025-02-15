package com.service;

import com.dto.request.AnswerRequest;
import com.dto.response.AnswerResponse;
import com.dto.response.ApiResponse;
import com.entity.AnswerEntity;
import com.entity.QuestionEntity;
import com.exception.custom.NotFoundException;
import com.repository.AnswerRepository;
import com.repository.QuestionRepository;
import com.util.BuildResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    private final ModelMapper modelMapper;


    public ApiResponse<AnswerResponse> createAnswers(AnswerRequest request) {
        AnswerEntity answerEntity = new AnswerEntity();
        answerEntity.setContent(request.getContent());
        answerEntity.setCorrect(request.getCorrect());
        if (request.getQuestionId() != null) {
            QuestionEntity questionEntity = questionRepository.findById(request.getQuestionId())
                    .orElseThrow(() -> new NotFoundException("Question not found"));
            answerEntity.setQuestion(questionEntity);
        }

        answerEntity = answerRepository.save(answerEntity);

        return BuildResponse.buildApiResponse(
                HttpStatus.CREATED.value(),
                "Tạo câu trả lời thành công",
                null,
                modelMapper.map(answerEntity, AnswerResponse.class)
        );
    }

    public ApiResponse<AnswerResponse> updateAnswers(Long answerId, AnswerRequest answerRequest) {
        AnswerEntity answerEntity= answerRepository.findById(answerId).orElseThrow(() -> new NotFoundException("Không tìm thấy Id câu trả lời!"));
        answerEntity.setContent(answerRequest.getContent());
        answerEntity.setCorrect(answerRequest.getCorrect());
        answerRepository.save(answerEntity);
        return BuildResponse.buildApiResponse(
                HttpStatus.OK.value(),
                "Thay đổi thông tin câu trả lời!",
                null,
                modelMapper.map(answerEntity, AnswerResponse.class)
        );
    }

    public ApiResponse<String> deleteAnswer(Long answerId) {
        if(answerRepository.existsById(answerId)) {
            Optional<AnswerEntity> answerEntity =this.answerRepository.findById(answerId);
            AnswerEntity answer = answerEntity.get();
            answer.setQuestion(null);
            answerRepository.deleteById(answerId);
        } else {
            throw new NotFoundException("Không tìm thấy Id môn học!");
        }
        return BuildResponse.buildApiResponse(
                HttpStatus.OK.value(),
                "Thành công!",
                "Bạn đã xóa câu trả lời thành công!",
                null
        );
    }

}
