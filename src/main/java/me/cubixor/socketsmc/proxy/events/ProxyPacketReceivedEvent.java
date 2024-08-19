package me.cubixor.socketsmc.proxy.events;

import me.cubixor.socketsmc.common.packets.Packet;
import me.cubixor.socketsmc.proxy.ProxyEvent;

public interface ProxyPacketReceivedEvent extends ProxyEvent {
    Packet getPacket();

    String getServer();
}
