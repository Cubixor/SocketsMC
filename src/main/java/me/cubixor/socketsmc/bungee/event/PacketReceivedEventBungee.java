package me.cubixor.socketsmc.bungee.event;

import me.cubixor.socketsmc.common.packets.Packet;
import net.md_5.bungee.api.plugin.Event;

public class PacketReceivedEventBungee extends Event {

    private final Packet packet;
    private final String server;

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
