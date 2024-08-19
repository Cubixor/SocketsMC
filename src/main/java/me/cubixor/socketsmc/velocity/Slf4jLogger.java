package me.cubixor.socketsmc.velocity;

import me.cubixor.socketsmc.proxy.CommonLogger;
import org.slf4j.Logger;

import java.util.logging.Level;

public class Slf4jLogger implements CommonLogger {
    private final Logger logger;

    public Slf4jLogger(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void log(Level level, String message) {
        logger.atLevel(parseLevel(level)).setMessage(message).log();
    }

    @Override
    public void log(Level level, String message, Throwable throwable) {
        logger.atLevel(parseLevel(level)).setMessage(message).setCause(throwable).log();
    }


    private org.slf4j.event.Level parseLevel(Level level) {
        if (level == Level.INFO) {
            return org.slf4j.event.Level.INFO;
        } else if (level == Level.WARNING) {
            return org.slf4j.event.Level.WARN;
        } else if (level == Level.SEVERE) {
            return org.slf4j.event.Level.ERROR;
        } else {
            return org.slf4j.event.Level.DEBUG;
        }
    }
}
