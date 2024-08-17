package me.cubixor.socketsmc.common.packets;

import java.util.Set;

public class TargetPacket {

    private final Packet packet;
    private final Set<java.lang.String> servers;

    public TargetPacket(Packet packet, Set<java.lang.String> servers) {
        this.packet = packet;
        this.servers = servers;
    }

    public Packet getPacket() {
        return packet;
    }

    public Set<java.lang.String> getServers() {
        return servers;
    }
}
