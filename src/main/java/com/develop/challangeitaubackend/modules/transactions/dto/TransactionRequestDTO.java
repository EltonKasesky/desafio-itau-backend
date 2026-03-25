package com.develop.challangeitaubackend.modules.transactions.dto;

import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.OffsetDateTime;

@AllArgsConstructor
@Data
public class TransactionRequestDTO {
    @Positive(message = "O valor da transação deve ser maior que zero.")
    private Double valor;

    @PastOrPresent(message = "A transação não pode ser feita no futuro.")
    private OffsetDateTime dataHora;
}
