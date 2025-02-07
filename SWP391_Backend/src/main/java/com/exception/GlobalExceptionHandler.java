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
            InvalidTokenException.class,
            NonUniqueResultException.class,
            IllegalArgumentException.class,
            IOException.class,
            ArrayIndexOutOfBoundsException.class,
            InvalidRequestInput.class
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
        apiResponse.setMessage("Exception!");
        apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        apiResponse.setErrorMessage(Objects.requireNonNull(e.getFieldError()).getDefaultMessage());
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
}