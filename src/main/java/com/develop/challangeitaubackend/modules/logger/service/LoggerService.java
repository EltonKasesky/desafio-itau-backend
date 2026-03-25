package com.develop.challangeitaubackend.modules.logger.service;

import com.develop.challangeitaubackend.infrastructure.logger.domain.Logger;
import com.develop.challangeitaubackend.infrastructure.logger.factory.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LoggerService {
    public Logger getLogs(){
        return LoggerFactory.getLogs();
    }
}
