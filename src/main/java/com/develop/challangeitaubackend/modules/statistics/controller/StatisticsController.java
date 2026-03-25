package com.develop.challangeitaubackend.modules.statistics.controller;

import com.develop.challangeitaubackend.modules.statistics.dto.StatisticsResponseDTO;
import com.develop.challangeitaubackend.modules.statistics.service.StatisticsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estatisticas")
@Tag(name = "Estatísticas", description = "Retorna as estatistícas das transações recentes")
public class StatisticsController {
    private StatisticsService service;

    public StatisticsController(StatisticsService service) {
        this.service = service;
    }

    @Operation(
            summary = "Retorna as estatísticas de transações",
            description = "Retorna as estatísticas das transações recentes realizadas."
    ) @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Estatísticas retornadas com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro no servidor ao retornar estatísticas")
    })
    @GetMapping
    public ResponseEntity<StatisticsResponseDTO> getStatistics(){
        return ResponseEntity.ok().body(service.getStatistics());
    }
}
