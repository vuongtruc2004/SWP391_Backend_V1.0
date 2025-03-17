package com.exception;

import com.dto.response.ApiResponse;
import com.exception.custom.*;
import com.util.BuildResponse;
import org.hibernate.NonUniqueResultException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {
            NotFoundException.class,
            HttpMessageNotReadableException.class,
            MethodArgumentTypeMismatchException.class,
            RoleException.class,
            UserException.class,
            SubjectException.class,
            InvalidTokenException.class,
            NonUniqueResultException.class,
            IllegalArgumentException.class,
            IOException.class,
            ArrayIndexOutOfBoundsException.class,
            InvalidRequestInput.class,
            CourseException.class,
            OrderException.class,
            QuestionException.class,
            PurchaseException.class
    })
    public ResponseEntity<ApiResponse<Void>> handleException(Exception e) {
        ApiResponse<Void> apiResponse = BuildResponse.buildApiResponse(
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage(),
                "Exception!",
                null
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ApiResponse<Object> apiResponse = new ApiResponse<>();
        apiResponse.setMessage(Objects.requireNonNull(e.getFieldError()).getDefaultMessage());
        apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        apiResponse.setErrorMessage("Exception!");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    @ExceptionHandler(value = {UserRequestException.class})
    public ResponseEntity<ApiResponse<Map<String, String>>> handleUserRequestException(UserRequestException exception) {
        String message = exception.getMessage();
        String[] errors = message.split(";");
        Map<String, String> errorMap = new HashMap<>();
        for (String error : errors) {
            String key = error.split(":")[0];
            String value = error.split(":")[1];
            errorMap.put(key, value);
        }
        ApiResponse<Map<String, String>> errorResponse = ApiResponse.<Map<String, String>>builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .data(errorMap)
                .build();
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(value = StorageException.class)
    public ResponseEntity<ApiResponse<Object>> handleStorageException(StorageException e) {
        ApiResponse<Object> apiResponse = new ApiResponse<>();
        apiResponse.setMessage(e.getMessage());
        apiResponse.setErrorMessage("Exception upload file");
        apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }



    @ExceptionHandler(value = TitleQuizException.class)
    public ResponseEntity<ApiResponse<Void>> handleTitleQuizException(Exception e) {
        ApiResponse<Void> apiResponse = BuildResponse.buildApiResponse(
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage(),
                "Title_Quiz_Exception",
                null
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    @ExceptionHandler(value = TitleQuestionException.class)
    public ResponseEntity<ApiResponse<Void>> handleTitleQuestionException(Exception e) {
        ApiResponse<Void> apiResponse = BuildResponse.buildApiResponse(
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage(),
                "Title_Question_Exception",
                null
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    @ExceptionHandler(value = ChapterException.class)
    public ResponseEntity<ApiResponse<Void>> handleChapterException(Exception e) {
        ApiResponse<Void> apiResponse = BuildResponse.buildApiResponse(
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage(),
                "Chapter_Exception",
                null
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }
}