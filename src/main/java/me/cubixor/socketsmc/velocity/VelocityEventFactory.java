package me.cubixor.socketsmc.velocity;

import me.cubixor.socketsmc.common.packets.Packet;
import me.cubixor.socketsmc.proxy.EventFactory;
import me.cubixor.socketsmc.proxy.events.ProxyPacketReceivedEvent;
import me.cubixor.socketsmc.proxy.events.ProxySocketConnectedEvent;
import me.cubixor.socketsmc.proxy.events.ProxySocketDisconnectedEvent;
import me.cubixor.socketsmc.velocity.events.PacketReceivedEventVelocity;
import me.cubixor.socketsmc.velocity.events.SocketConnectedEventVelocity;
import me.cubixor.socketsmc.velocity.events.SocketDisconnectedEventVelocity;

public class VelocityEventFactory implements EventFactory {
    @Override
    public ProxyPacketReceivedEvent createPacketReceivedEvent(Packet packet, String server) {
        return new PacketReceivedEventVelocity(packet, server);
    }

    @Override
    public ProxySocketConnectedEvent createSocketConnectedEvent(String server) {
        return new SocketConnectedEventVelocity(server);
    }

    @Override
    public ProxySocketDisconnectedEvent createSocketDisconnectedEvent(String server) {
        return new SocketDisconnectedEventVelocity(server);
    }
}
