package com.develop.challengeitaubackend.modules.transactions.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionEntity {
    private BigDecimal valor;
    private OffsetDateTime dataHora;
}
