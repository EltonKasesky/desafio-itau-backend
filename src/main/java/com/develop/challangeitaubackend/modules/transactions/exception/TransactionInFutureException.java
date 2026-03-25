package com.develop.challangeitaubackend.modules.transactions.exception;

public class TransactionInFutureException extends RuntimeException {
    public TransactionInFutureException(String message) {
        super(message);
    }
}
