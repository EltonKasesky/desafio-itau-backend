package com.develop.challengeitaubackend.modules.transactions.repository;

import com.develop.challengeitaubackend.modules.transactions.entity.TransactionEntity;

import java.util.ArrayList;
import java.util.List;

public class TransactionRepository {
    private static List<TransactionEntity> transactions;

    private TransactionRepository() {}

    public static List<TransactionEntity> getTransactions() {
        if (transactions == null) {
            transactions = new ArrayList<>();
        }

        return transactions;
    }
}
