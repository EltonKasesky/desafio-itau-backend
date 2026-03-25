package com.develop.challangeitaubackend.modules.transactions.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.OffsetDateTime;

@AllArgsConstructor
@Data
public class TransactionRequestDTO {
    @Schema(description = "Valor da transação", example = "150.0")
    private Double valor;

    @Schema(description = "Data e hora da transação (não pode ser futura)", example = "2026-03-01T10:00:00-03:00")
    private OffsetDateTime dataHora;
}
