package me.cubixor.socketsmc.proxy.events;

import me.cubixor.socketsmc.proxy.ProxyEvent;

public interface ProxySocketConnectedEvent extends ProxyEvent {

    String getServer();
}