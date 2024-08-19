package me.cubixor.socketsmc.bungee.events;

import me.cubixor.socketsmc.common.packets.Packet;
import me.cubixor.socketsmc.proxy.events.ProxyPacketReceivedEvent;
import net.md_5.bungee.api.plugin.Event;

public class PacketReceivedEventBungee extends Event implements ProxyPacketReceivedEvent {

    final Packet packet;
    final String server;

    public PacketReceivedEventBungee(Packet packet, String server) {
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
