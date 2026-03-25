package com.develop.challangeitaubackend.modules.statistics.service;

import com.develop.challangeitaubackend.modules.statistics.dto.StatisticsResponseDTO;
import com.develop.challangeitaubackend.modules.transactions.entity.TransactionEntity;
import com.develop.challangeitaubackend.modules.transactions.factory.TransactionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.DoubleSummaryStatistics;

@Service
public class StatisticsService {
    @Value("${time-limit-for-transactions}")
    Integer timeLimit;

    public StatisticsResponseDTO getStatistics() {
        DoubleSummaryStatistics statistics = TransactionFactory.getTransactions()
                .stream()
                .filter(t -> t.getDataHora().isAfter(getLimitForTransactions()))
                .mapToDouble(TransactionEntity::getValor)
                .summaryStatistics();

        return new StatisticsResponseDTO(statistics);
    }

    private OffsetDateTime getLimitForTransactions() {
        return OffsetDateTime.now().minusSeconds(timeLimit);
    }
}
