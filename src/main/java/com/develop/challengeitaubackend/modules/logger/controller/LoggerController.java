package com.develop.challengeitaubackend.modules.logger.controller;

import com.develop.challengeitaubackend.infrastructure.logger.domain.Logger;
import com.develop.challengeitaubackend.modules.logger.service.LoggerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/log")
@Tag(name = "Logs", description = "Logs do sistema em geral")
public class LoggerController {
    private final LoggerService service;

    public LoggerController(LoggerService service) {
        this.service = service;
    }

    @Operation(
            summary = "Retorna todos os logs",
            description = "Retorna todos os logs gerados pelas requisições."
    ) @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Logs retornados com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro no servidor ao retornar logs")
    })
    @GetMapping
    public ResponseEntity<Logger> getLogs(){
        return ResponseEntity.ok().body(service.getLogs());
    }
}
