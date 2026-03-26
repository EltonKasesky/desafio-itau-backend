package com.develop.challengeitaubackend.modules.transactions.service;

import com.develop.challengeitaubackend.infrastructure.logger.domain.Log;
import com.develop.challengeitaubackend.infrastructure.logger.enums.LogTypesEnum;
import com.develop.challengeitaubackend.infrastructure.logger.repository.LoggerRepository;
import com.develop.challengeitaubackend.modules.transactions.dto.TransactionRequestDTO;
import com.develop.challengeitaubackend.modules.transactions.entity.TransactionEntity;
import com.develop.challengeitaubackend.modules.transactions.exception.TransactionInFutureException;
import com.develop.challengeitaubackend.modules.transactions.exception.TransactionNegativeException;
import com.develop.challengeitaubackend.modules.transactions.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Service
public class TransactionService {
    public void addTransaction(TransactionRequestDTO dto) {
        validateTransaction(dto);

        TransactionRepository.getTransactions().add(
                new TransactionEntity(
                        dto.getValor(),
                        dto.getDataHora()
                )
        );

        LoggerRepository.addLog(
                new Log(
                        LogTypesEnum.ADD,
                        String.format("Transação adicionada com valor de %.2f em %s", dto.getValor(), dto.getDataHora())
                )
        );
    }

    private void validateTransaction(TransactionRequestDTO dto) {
        if (dto.getValor().compareTo(BigDecimal.ZERO) <= 0)
            throw new TransactionNegativeException("O valor da transação deve ser maior que zero.");

        if (dto.getDataHora().isAfter(OffsetDateTime.now()))
            throw new TransactionInFutureException("A transação não pode ser feita no futuro.");
    }

    public void deleteTransactions(){
        if (!TransactionRepository.getTransactions().isEmpty()) {
            TransactionRepository.getTransactions().clear();

            LoggerRepository.addLog(
                    new Log(
                            LogTypesEnum.DELETE,
                            "Transações Deletadas com sucesso."
                    )
            );
        }
    }
}
