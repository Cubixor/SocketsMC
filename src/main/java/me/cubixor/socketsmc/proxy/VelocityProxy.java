package me.cubixor.socketsmc.proxy;


import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;

public class VelocityProxy implements Proxy {

    private final Plugin plugin;
    private final ProxyServer server;

    public VelocityProxy(Plugin plugin, ProxyServer server) {
        this.plugin = plugin;
        this.server = server;
    }

    @Override
    public void runAsync(Runnable task) {
        server.getScheduler().buildTask(plugin, task).schedule();
    }

    public void fireEvent(ProxyEvent event) {
        server.getEventManager().fire(event);
    }
}
