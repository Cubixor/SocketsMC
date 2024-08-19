package me.cubixor.socketsmc.velocity.events;

import me.cubixor.socketsmc.common.packets.Packet;
import me.cubixor.socketsmc.proxy.events.ProxyPacketReceivedEvent;

public class PacketReceivedEventVelocity implements ProxyPacketReceivedEvent {

    final Packet packet;
    final String server;

    public PacketReceivedEventVelocity(Packet packet, String server) {
        this.packet = packet;
        this.server = server;
    }

    public Packet getPacket() {
        return packet;
    }

    public String getServer() {
        return server;
    }
}
