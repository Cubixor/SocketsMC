package me.cubixor.socketsmc.bungee;


import me.cubixor.socketsmc.bungee.event.PacketReceivedEventBungee;
import me.cubixor.socketsmc.common.packets.Packet;
import net.md_5.bungee.api.ProxyServer;

import java.io.*;
import java.net.ServerSocket;
import java.util.logging.Level;


public class SocketServerReceiver {

    private final SocketServer socketServer;

    public SocketServerReceiver(SocketServer socketServer) {
        this.socketServer = socketServer;
    }

    protected void serverMessageReader(ServerSocket socket, java.lang.String server, ObjectInputStream in) throws IOException {
        while (!socket.isClosed()) {
            try {
                Object object = in.readObject();
                Packet packet = (Packet) object;

                if (socketServer.isDebug()) {
                    java.lang.String className = packet.getClass().getName();
                    socketServer.getLogger().log(Level.INFO, () -> "Packet received: " + className + " from server: " + server + "\n" + packet);
                }

                ProxyServer.getInstance().getPluginManager().callEvent(new PacketReceivedEventBungee(packet, server));

            } catch (ClassNotFoundException |
                     InvalidClassException |
                     StreamCorruptedException |
                     OptionalDataException e) {
                if (socketServer.isDebug()) {
                    socketServer.getLogger().log(Level.WARNING, "Error while parsing a packet", e);
                }

            }
        }

    }

}