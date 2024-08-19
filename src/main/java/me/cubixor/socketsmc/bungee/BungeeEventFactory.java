package me.cubixor.socketsmc.bungee;

import me.cubixor.socketsmc.bungee.events.PacketReceivedEventBungee;
import me.cubixor.socketsmc.bungee.events.SocketConnectedEventBungee;
import me.cubixor.socketsmc.bungee.events.SocketDisconnectedEventBungee;
import me.cubixor.socketsmc.common.packets.Packet;
import me.cubixor.socketsmc.proxy.EventFactory;
import me.cubixor.socketsmc.proxy.events.ProxyPacketReceivedEvent;
import me.cubixor.socketsmc.proxy.events.ProxySocketConnectedEvent;
import me.cubixor.socketsmc.proxy.events.ProxySocketDisconnectedEvent;

public class BungeeEventFactory implements EventFactory {
    @Override
    public ProxyPacketReceivedEvent createPacketReceivedEvent(Packet packet, String server) {
        return new PacketReceivedEventBungee(packet, server);
    }

    @Override
    public ProxySocketConnectedEvent createSocketConnectedEvent(String server) {
        return new SocketConnectedEventBungee(server);
    }

    @Override
    public ProxySocketDisconnectedEvent createSocketDisconnectedEvent(String server) {
        return new SocketDisconnectedEventBungee(server);
    }
}
