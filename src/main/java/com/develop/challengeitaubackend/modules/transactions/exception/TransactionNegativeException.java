package com.develop.challengeitaubackend.modules.transactions.exception;

public class TransactionNegativeException extends RuntimeException {
    public TransactionNegativeException(String message) {
        super(message);
    }
}
