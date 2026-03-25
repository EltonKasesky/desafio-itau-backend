package com.develop.challangeitaubackend.modules.transactions.controller;

import com.develop.challangeitaubackend.modules.transactions.dto.TransactionRequestDTO;
import com.develop.challangeitaubackend.modules.transactions.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transacao")
@Tag(name = "Transações", description = "Gerenciamento de transações")
public class TransactionController {
    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @Operation(
            summary = "Adiciona uma nova transação",
            description = "Cria uma transação. Retorna 422 se o valor for negativo ou a data estiver no futuro."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Transação criada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados inválidos (valor negativo ou data futura)"),
            @ApiResponse(responseCode = "400", description = "Requisição malformada")
    })
    @PostMapping
    public ResponseEntity<Void> addTransaction(@Valid @RequestBody TransactionRequestDTO dto){
        service.addTransaction(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(
            summary = "Delete todas as transações",
            description = "Remove todas as transações da memória. Retorna 500 caso houver erros internos."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Transações deletadas com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro no servidor ao deletar transações")
    })
    @DeleteMapping
    public ResponseEntity<Void> deleteTransaction(){
        service.deleteTransactions();
        return ResponseEntity.ok().build();
    }
}
