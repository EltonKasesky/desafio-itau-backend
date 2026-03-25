package com.develop.challangeitaubackend.modules.transactions.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.OffsetDateTime;

@AllArgsConstructor
@Data
public class TransactionRequestDTO {
    private Double valor;
    private OffsetDateTime dataHora;
}
