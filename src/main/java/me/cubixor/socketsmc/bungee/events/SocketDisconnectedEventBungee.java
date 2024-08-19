package me.cubixor.socketsmc.bungee.events;

import me.cubixor.socketsmc.proxy.events.ProxySocketDisconnectedEvent;
import net.md_5.bungee.api.plugin.Event;

public class SocketDisconnectedEventBungee extends Event implements ProxySocketDisconnectedEvent {

    private final String server;

    public SocketDisconnectedEventBungee(String server) {
        this.server = server;
    }

    public String getServer() {
        return server;
    }
}
