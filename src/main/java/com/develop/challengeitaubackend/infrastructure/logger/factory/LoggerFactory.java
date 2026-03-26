package com.develop.challengeitaubackend.infrastructure.logger.factory;

import com.develop.challengeitaubackend.infrastructure.logger.domain.Log;
import com.develop.challengeitaubackend.infrastructure.logger.domain.Logger;

public class LoggerFactory {
    private static Logger logger;

    public static void addLog(Log log){
        if (logger == null){
            logger = new Logger();
        }

        logger.addLog(log);
    }

    public static Logger getLogs(){
       return logger;
    }
}
