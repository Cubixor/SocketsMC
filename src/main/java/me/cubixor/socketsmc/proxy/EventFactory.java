package me.cubixor.socketsmc.proxy;

import me.cubixor.socketsmc.common.packets.Packet;
import me.cubixor.socketsmc.proxy.events.ProxyPacketReceivedEvent;
import me.cubixor.socketsmc.proxy.events.ProxySocketConnectedEvent;
import me.cubixor.socketsmc.proxy.events.ProxySocketDisconnectedEvent;

public interface EventFactory {

    ProxyPacketReceivedEvent createPacketReceivedEvent(Packet packet, String server);

    ProxySocketConnectedEvent createSocketConnectedEvent(String server);

    ProxySocketDisconnectedEvent createSocketDisconnectedEvent(String server);
}
