package com.exception;

import com.dto.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> notFoundException(NotFoundException e) {
        ApiResponse<Object> apiResponse = new ApiResponse<>();
        apiResponse.setMessage("Exception !");
        apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        apiResponse.setErrorMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        ApiResponse<Object> apiResponse = new ApiResponse<>();
        apiResponse.setMessage("Exception !");
        apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        apiResponse.setErrorMessage(Objects.requireNonNull(e.getFieldError()).getDefaultMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<Object>> httpMessageNotReadableException(HttpMessageNotReadableException e) {
        ApiResponse<Object> apiResponse = new ApiResponse<>();
        apiResponse.setMessage("Exception !");
        apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        apiResponse.setErrorMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiResponse<Object>> methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        ApiResponse<Object> apiResponse = new ApiResponse<>();
        apiResponse.setMessage("Exception !");
        apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        apiResponse.setErrorMessage(e.getMessage());
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