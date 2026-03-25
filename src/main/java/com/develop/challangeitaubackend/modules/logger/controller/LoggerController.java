package com.develop.challangeitaubackend.modules.logger.controller;

import com.develop.challangeitaubackend.infrastructure.logger.domain.Logger;
import com.develop.challangeitaubackend.modules.logger.service.LoggerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/log")
public class LoggerController {
    private final LoggerService service;

    public LoggerController(LoggerService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Logger> getLogs(){
        try {
            return ResponseEntity.ok().body(service.getLogs());
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
