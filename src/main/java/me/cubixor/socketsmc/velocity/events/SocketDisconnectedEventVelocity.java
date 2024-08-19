package me.cubixor.socketsmc.velocity.events;

import me.cubixor.socketsmc.proxy.events.ProxySocketDisconnectedEvent;

public class SocketDisconnectedEventVelocity implements ProxySocketDisconnectedEvent {

    private final String server;

    public SocketDisconnectedEventVelocity(String server) {
        this.server = server;
    }

    public String getServer() {
        return server;
    }
}
