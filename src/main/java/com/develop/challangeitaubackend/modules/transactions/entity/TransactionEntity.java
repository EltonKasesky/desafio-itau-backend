package com.develop.challangeitaubackend.modules.transactions.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionEntity {
    private Double valor;
    private OffsetDateTime dataHora;
}
