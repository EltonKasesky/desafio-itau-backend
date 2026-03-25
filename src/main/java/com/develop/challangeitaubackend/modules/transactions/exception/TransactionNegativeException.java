package com.develop.challangeitaubackend.modules.transactions.exception;

public class TransactionNegativeException extends RuntimeException {
    public TransactionNegativeException(String message) {
        super(message);
    }
}
