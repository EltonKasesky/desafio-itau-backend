package com.develop.challengeitaubackend.infrastructure.logger.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Data
@Component
public class Logger {
    private List<Log> logs;

    public Logger(){
        logs = new LinkedList<>();
    }

    public void addLog(Log log){
        logs.add(log);
    }
}
