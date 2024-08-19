package me.cubixor.socketsmc.velocity;


import com.velocitypowered.api.proxy.ProxyServer;
import me.cubixor.socketsmc.proxy.CommonLogger;
import me.cubixor.socketsmc.proxy.EventFactory;
import me.cubixor.socketsmc.proxy.Proxy;
import me.cubixor.socketsmc.proxy.ProxyEvent;
import org.slf4j.Logger;

public class VelocityProxy implements Proxy {

    private final Object plugin;
    private final ProxyServer server;
    private final Logger logger;

    public VelocityProxy(Object plugin, ProxyServer server, Logger logger) {
        this.plugin = plugin;
        this.server = server;
        this.logger = logger;
    }

    @Override
    public CommonLogger getLogger() {
        return new Slf4jLogger(logger);
    }

    @Override
    public EventFactory getEventFactory() {
        return new VelocityEventFactory();
    }

    @Override
    public void runAsync(Runnable task) {
        server.getScheduler().buildTask(plugin, task).schedule();
    }

    public void fireEvent(ProxyEvent event) {
        server.getEventManager().fire(event);
    }
}
