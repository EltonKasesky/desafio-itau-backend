package com.develop.challangeitaubackend.infrastructure.logger.domain;

import com.develop.challangeitaubackend.infrastructure.logger.enums.LogTypesEnum;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Log {
    private final LogTypesEnum type;
    private final String log;
    private final LocalDateTime timestamp;

    public Log(LogTypesEnum type, String log){
        this.type = type;
        this.log = log;
        this.timestamp = LocalDateTime.now();
    }
}
