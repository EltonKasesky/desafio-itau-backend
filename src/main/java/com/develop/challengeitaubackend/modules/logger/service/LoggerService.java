package com.develop.challengeitaubackend.modules.logger.service;

import com.develop.challengeitaubackend.infrastructure.logger.domain.Logger;
import com.develop.challengeitaubackend.infrastructure.logger.repository.LoggerRepository;
import org.springframework.stereotype.Service;

@Service
public class LoggerService {
    public Logger getLogs(){
        return LoggerRepository.getLogs();
    }
}
