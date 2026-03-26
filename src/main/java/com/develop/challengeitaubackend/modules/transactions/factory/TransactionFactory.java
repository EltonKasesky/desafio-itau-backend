package com.develop.challengeitaubackend.modules.transactions.factory;

import com.develop.challengeitaubackend.modules.transactions.entity.TransactionEntity;

import java.util.ArrayList;
import java.util.List;

public class TransactionFactory {
    private static List<TransactionEntity> transactions;

    private TransactionFactory() {}

    public static List<TransactionEntity> getTransactions() {
        if (transactions == null) {
            transactions = new ArrayList<>();
        }

        return transactions;
    }
}
