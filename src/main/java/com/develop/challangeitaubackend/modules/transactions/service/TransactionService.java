package com.develop.challangeitaubackend.modules.transactions.service;

import com.develop.challangeitaubackend.infrastructure.logger.domain.Log;
import com.develop.challangeitaubackend.infrastructure.logger.enums.LogTypesEnum;
import com.develop.challangeitaubackend.infrastructure.logger.factory.LoggerFactory;
import com.develop.challangeitaubackend.modules.transactions.dto.TransactionRequestDTO;
import com.develop.challangeitaubackend.modules.transactions.entity.TransactionEntity;
import com.develop.challangeitaubackend.modules.transactions.exception.TransactionInFutureException;
import com.develop.challangeitaubackend.modules.transactions.exception.TransactionNegativeException;
import com.develop.challangeitaubackend.modules.transactions.factory.TransactionFactory;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class TransactionService {
    public void addTransaction(TransactionRequestDTO dto)
    throws TransactionInFutureException, TransactionNegativeException {
        validateTransaction(dto);

        TransactionFactory.getTransactions().add(
                new TransactionEntity(
                        dto.getValor(),
                        dto.getDataHora()
                )
        );

        LoggerFactory.addLog(
                new Log(
                        LogTypesEnum.ADD,
                        String.format("Transação adicionada com valor de %.2f em " + dto.getDataHora(), dto.getValor())
                )
        );
    }

    private void validateTransaction(TransactionRequestDTO dto) {
        if (dto.getValor() <= 0.0)
            throw new TransactionNegativeException("O valor da transação deve ser maior que zero.");

        if (dto.getDataHora().isAfter(OffsetDateTime.now()))
            throw new TransactionInFutureException("A transação não pode ser feita no futuro.");
    }

    public void deleteTransactions(){
        if (!TransactionFactory.getTransactions().isEmpty()) {
            TransactionFactory.getTransactions().clear();

            LoggerFactory.addLog(
                    new Log(
                            LogTypesEnum.DELETE,
                            "Transações Deletadas com sucesso."
                    )
            );
        }
    }
}
