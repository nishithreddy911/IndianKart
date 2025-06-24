package com.nishi.user.exception;

import com.nishi.user.dto.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.Serial;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @Serial
    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<APIResponse<?>> handleInvalidRequest(InvalidRequestException ex) {
        return ResponseEntity.badRequest().body(new APIResponse<>(false, ex.getMessage(), null, LocalDateTime.now()));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<APIResponse<?>> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIResponse<>(false, ex.getMessage(), null, LocalDateTime.now()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIResponse<?>> handleOtherExceptions(Exception ex) {
        System.out.println(ex);
        return ResponseEntity.internalServerError().body(new APIResponse<>(false, "This is not we usually expect but something unexpected occurred", null, LocalDateTime.now()));
    }
}
