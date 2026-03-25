package com.develop.challangeitaubackend.infrastructure.handler;

import com.develop.challangeitaubackend.modules.transactions.exception.TransactionInFutureException;
import com.develop.challangeitaubackend.modules.transactions.exception.TransactionNegativeException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({TransactionNegativeException.class, TransactionInFutureException.class})
    public ResponseEntity<Void> handleUnprocessableExceptions(RuntimeException e) {
        return ResponseEntity.unprocessableEntity().build();
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Void> handleBadRequest(RuntimeException e) {
        return ResponseEntity.badRequest().build();
    }
}
