package com.develop.challengeitaubackend.modules.transactions.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@AllArgsConstructor
@Data
public class TransactionRequestDTO {
    @Schema(description = "Valor da transação", example = "150.0")
    @NotNull
    @Positive
    private BigDecimal valor;

    @Schema(description = "Data e hora da transação (não pode ser futura)", example = "2026-03-01T10:00:00-03:00")
    @NotNull
    @PastOrPresent
    private OffsetDateTime dataHora;
}
