package me.cubixor.socketsmc.proxy;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Event;
import net.md_5.bungee.api.plugin.Plugin;

public class BungeeProxy implements Proxy {

    private final Plugin plugin;

    public BungeeProxy(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void runAsync(Runnable task) {
        ProxyServer.getInstance().getScheduler().runAsync(plugin, task);
    }

    public void fireEvent(ProxyEvent event) {
        ProxyServer.getInstance().getPluginManager().callEvent((Event) event);
    }
}
