package com.develop.challengeitaubackend.modules.statistics.service;

import com.develop.challengeitaubackend.modules.statistics.dto.StatisticsResponseDTO;
import com.develop.challengeitaubackend.modules.transactions.factory.TransactionFactory;
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
                .mapToDouble(t -> t.getValor().doubleValue())
                .summaryStatistics();

        return new StatisticsResponseDTO(
                statistics.getCount(),
                statistics.getSum(),
                statistics.getCount() > 0 ? statistics.getMin() : 0.0,
                statistics.getCount() > 0 ? statistics.getMax() : 0.0,
                statistics.getAverage()
        );
    }

    private OffsetDateTime getLimitForTransactions() {
        return OffsetDateTime.now().minusSeconds(timeLimit);
    }
}
