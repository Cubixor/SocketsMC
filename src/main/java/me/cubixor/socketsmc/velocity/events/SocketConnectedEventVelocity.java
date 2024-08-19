package me.cubixor.socketsmc.velocity.events;

import me.cubixor.socketsmc.proxy.events.ProxySocketConnectedEvent;

public class SocketConnectedEventVelocity implements ProxySocketConnectedEvent {

    private final String server;

    public SocketConnectedEventVelocity(String server) {
        this.server = server;
    }

    public String getServer() {
        return server;
    }
}
