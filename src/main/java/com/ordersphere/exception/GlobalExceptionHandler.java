package com.ordersphere.exception;

import com.ordersphere.core.api.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleOrderNotFound(OrderNotFoundException ex) {
        return ResponseEntity
                .status(ex.getErrorCode().httpCode())
                .body(new ErrorResponse(
                        ex.getErrorCode().toString(),
                        ex.getMessage(),
                        Instant.now(),
                        List.of(),
                        null
                ));
    }
}
