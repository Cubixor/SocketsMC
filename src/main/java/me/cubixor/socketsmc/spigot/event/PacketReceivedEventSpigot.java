package me.cubixor.socketsmc.spigot.event;

import me.cubixor.socketsmc.common.packets.Packet;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PacketReceivedEventSpigot extends Event {

    private static final HandlerList HANDLERS = new HandlerList();
    private final Packet packet;

    public PacketReceivedEventSpigot(Packet packet) {
        this.packet = packet;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public Packet getPacket() {
        return packet;
    }
}
