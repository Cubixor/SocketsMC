package me.cubixor.socketsmc.proxy.events;

import me.cubixor.socketsmc.proxy.ProxyEvent;

public interface ProxySocketDisconnectedEvent extends ProxyEvent {

    String getServer();
}