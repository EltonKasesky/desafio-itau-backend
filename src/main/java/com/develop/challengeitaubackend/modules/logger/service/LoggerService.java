package com.develop.challengeitaubackend.modules.logger.service;

import com.develop.challengeitaubackend.infrastructure.logger.domain.Logger;
import com.develop.challengeitaubackend.infrastructure.logger.factory.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LoggerService {
    public Logger getLogs(){
        return LoggerFactory.getLogs();
    }
}
