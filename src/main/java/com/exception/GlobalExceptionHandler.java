package com.exception;

import com.dto.response.RestResponseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value={BadCredentialsException.class})
    public ResponseEntity<RestResponseException<Object>> exception(Exception e) {
        RestResponseException<Object> restResponseException = new RestResponseException<>();
        restResponseException.setStausCode(HttpStatus.BAD_REQUEST.value());
        restResponseException.setErrorMessage(e.getMessage());
        restResponseException.setMessage("Exception !");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(restResponseException);
    }
    @ExceptionHandler(value={UserExistsException.class})
    public ResponseEntity<RestResponseException<Object>> UserExistsException(UserExistsException e) {
        RestResponseException<Object> restResponseException = new RestResponseException<>();
        restResponseException.setStausCode(HttpStatus.BAD_REQUEST.value());
        restResponseException.setErrorMessage(e.getMessage());
        restResponseException.setMessage("Exception !");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(restResponseException);
    }
}
