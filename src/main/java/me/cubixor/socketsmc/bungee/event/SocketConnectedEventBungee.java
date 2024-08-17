package me.cubixor.socketsmc.bungee.event;

import net.md_5.bungee.api.plugin.Event;

public class SocketConnectedEventBungee extends Event {

    private final String server;

    public SocketConnectedEventBungee(String server) {
        this.server = server;
    }

    public String getServer() {
        return server;
    }
}
