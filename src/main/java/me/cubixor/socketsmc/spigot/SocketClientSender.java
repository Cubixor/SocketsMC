package me.cubixor.socketsmc.spigot;


import me.cubixor.socketsmc.common.packets.NullPacket;
import me.cubixor.socketsmc.common.packets.Packet;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;

public class SocketClientSender {

    private final SocketClient socketClient;
    private final LinkedBlockingQueue<Packet> sendQueue = new LinkedBlockingQueue<>();

    public SocketClientSender(SocketClient socketClient) {
        this.socketClient = socketClient;
    }

    // Run this asynchronously!
    protected void send(Socket socket, ObjectOutputStream out) {
        while (!socket.isClosed()) {
            try {
                Packet packet = sendQueue.take();
                if (packet instanceof NullPacket) return;

                out.writeObject(packet);
                out.flush();
                out.reset();

                if (socketClient.isDebug()) {
                    socketClient.getLogger().log(Level.INFO, () -> "Sent packet: " + packet.getClassName() + "\n" + packet);
                }
            } catch (IOException | InterruptedException e) {
                socketClient.getLogger().log(Level.WARNING, "Failed to send packet", e);
            }
        }
    }

    public void close() {
        sendQueue.add(new NullPacket());
    }

    public void sendPacket(Packet packet) {
        sendQueue.add(packet);
    }
}
