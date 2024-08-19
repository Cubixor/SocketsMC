package me.cubixor.socketsmc.proxy;

import java.util.logging.Level;

public interface CommonLogger {

    void log(Level level, String message);

    void log(Level level, String message, Throwable throwable);
}
