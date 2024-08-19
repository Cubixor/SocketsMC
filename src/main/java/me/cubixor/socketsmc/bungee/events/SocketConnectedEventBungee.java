package me.cubixor.socketsmc.bungee.events;

import me.cubixor.socketsmc.proxy.events.ProxySocketConnectedEvent;
import net.md_5.bungee.api.plugin.Event;

public class SocketConnectedEventBungee extends Event implements ProxySocketConnectedEvent {

    private final String server;

    public SocketConnectedEventBungee(String server) {
        this.server = server;
    }

    public String getServer() {
        return server;
    }
}
