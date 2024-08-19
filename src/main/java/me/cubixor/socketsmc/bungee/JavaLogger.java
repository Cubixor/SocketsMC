package me.cubixor.socketsmc.bungee;

import me.cubixor.socketsmc.proxy.CommonLogger;

import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaLogger implements CommonLogger {

    private final Logger logger;

    public JavaLogger(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void log(Level level, String message) {
        logger.log(level, message);
    }

    @Override
    public void log(Level level, String message, Throwable throwable) {
        logger.log(level, message, throwable);
    }
}
