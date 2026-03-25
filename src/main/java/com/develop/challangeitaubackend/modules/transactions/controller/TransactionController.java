package com.develop.challangeitaubackend.modules.transactions.controller;

import com.develop.challangeitaubackend.modules.transactions.dto.TransactionRequestDTO;
import com.develop.challangeitaubackend.modules.transactions.exception.TransactionInFutureException;
import com.develop.challangeitaubackend.modules.transactions.exception.TransactionNegativeException;
import com.develop.challangeitaubackend.modules.transactions.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transacao")
public class TransactionController {
    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> addTransaction(@Valid @RequestBody TransactionRequestDTO dto){
        try {
            service.addTransaction(dto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (TransactionNegativeException e){
            return ResponseEntity.unprocessableEntity().build();
        } catch (TransactionInFutureException e){
            return ResponseEntity.unprocessableEntity().build();
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteTransaction(){
        try {
            service.deleteTransactions();
            return ResponseEntity.ok().build();
        } catch (RuntimeException e){
            return ResponseEntity.internalServerError().build();
        }
    }
}
