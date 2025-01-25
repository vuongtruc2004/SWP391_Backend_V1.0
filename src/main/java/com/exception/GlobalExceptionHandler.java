package com.exception;

import com.dto.response.RestResponseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<RestResponseException<Object>> notFoundException(NotFoundException e) {
        RestResponseException<Object> restResponseException = new RestResponseException<>();
        restResponseException.setMessage("Exception !");
        restResponseException.setStatusCode(HttpStatus.BAD_REQUEST.value());
        restResponseException.setErrorMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(restResponseException);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<RestResponseException<Object>> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        RestResponseException<Object> restResponseException = new RestResponseException<>();
        restResponseException.setMessage("Exception !");
        restResponseException.setStatusCode(HttpStatus.BAD_REQUEST.value());
        restResponseException.setErrorMessage(e.getFieldError().getDefaultMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(restResponseException);
    }
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<RestResponseException<Object>> httpMessageNotReadableException(HttpMessageNotReadableException e) {
        RestResponseException<Object> restResponseException = new RestResponseException<>();
        restResponseException.setMessage("Exception !");
        restResponseException.setStatusCode(HttpStatus.BAD_REQUEST.value());
        restResponseException.setErrorMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(restResponseException);
    }
    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public ResponseEntity<RestResponseException<Object>> methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        RestResponseException<Object> restResponseException = new RestResponseException<>();
        restResponseException.setMessage("Exception !");
        restResponseException.setStatusCode(HttpStatus.BAD_REQUEST.value());
        restResponseException.setErrorMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(restResponseException);
    }

    @ExceptionHandler(value = {UserRequestException.class})
    public ResponseEntity<RestResponseException<Map<String, String>>> handleUserRequestException(UserRequestException exception) {
        String message = exception.getMessage();
        String[] errors = message.split(";");
        Map<String, String> errorMap = new HashMap<>();
        for (String error : errors) {
            String key = error.split(":")[0];
            String value = error.split(":")[1];
            errorMap.put(key, value);
        }
        RestResponseException<Map<String, String>> errorResponse = RestResponseException.<Map<String, String>>builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .data(errorMap)
                .build();
        return ResponseEntity.badRequest().body(errorResponse);
    }
    }