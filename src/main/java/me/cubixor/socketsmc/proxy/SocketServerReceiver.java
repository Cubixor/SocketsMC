package me.cubixor.socketsmc.proxy;


import me.cubixor.socketsmc.common.packets.Packet;

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
                    socketServer.getLogger().log(Level.INFO, "Packet received: " + className + " from server: " + server + "\n" + packet);
                }

                EventFactory eventFactory = socketServer.getProxy().getEventFactory();
                socketServer.getProxy().fireEvent(eventFactory.createPacketReceivedEvent(packet, server));

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