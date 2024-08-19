package me.cubixor.socketsmc.proxy;


import me.cubixor.socketsmc.common.SocketConnection;
import me.cubixor.socketsmc.common.packets.Packet;
import me.cubixor.socketsmc.common.packets.TargetPacket;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;

public class SocketServerSender {

    private final SocketServer socketServer;
    private final LinkedBlockingQueue<TargetPacket> sendQueue = new LinkedBlockingQueue<>();

    public SocketServerSender(SocketServer socketServer) {
        this.socketServer = socketServer;
    }

    // Run this asynchronously!
    protected void send(ServerSocket socket) {
        while (!socket.isClosed()) {
            try {
                TargetPacket targetPacket = sendQueue.take();
                if (socket.isClosed()) return;
                Packet packet = targetPacket.getPacket();
                Set<java.lang.String> servers = targetPacket.getServers();

                for (java.lang.String server : servers) {
                    SocketConnection socketConnection = socketServer.getSpigotSocket(server);
                    if (socketConnection == null) continue;
                    ObjectOutputStream out = socketConnection.getOutputStream();

                    out.writeObject(packet);
                    out.flush();
                    out.reset();

                    if (socketServer.isDebug()) {
                        socketServer.getLogger().log(Level.INFO, "Sent packet: " + packet.getClassName() + " to server: " + server + "\n" + packet);
                    }
                }

            } catch (IOException | InterruptedException e) {
                socketServer.getLogger().log(Level.WARNING, "Error sending packet", e);
            }
        }
    }

    public void sendPacketToServer(Packet packet, java.lang.String server) {
        sendQueue.add(new TargetPacket(packet, Collections.singleton(server)));
    }

    public void sendPacketToAllExcept(Packet packet, java.lang.String exceptServer) {
        Set<java.lang.String> servers = getServersExcept(exceptServer);
        sendQueue.add(new TargetPacket(packet, servers));
    }

    private Set<java.lang.String> getServersExcept(java.lang.String server) {
        Set<java.lang.String> servers = new HashSet<>(socketServer.getSpigotSockets().keySet());
        servers.remove(server);
        return servers;
    }

}