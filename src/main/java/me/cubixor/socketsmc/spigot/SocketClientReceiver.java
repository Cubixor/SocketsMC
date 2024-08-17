package me.cubixor.socketsmc.spigot;

import me.cubixor.socketsmc.common.packets.Packet;
import me.cubixor.socketsmc.spigot.event.PacketReceivedEventSpigot;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.net.Socket;
import java.util.logging.Level;

class SocketClientReceiver {

    private final JavaPlugin plugin;
    private final SocketClient socketClient;

    SocketClientReceiver(JavaPlugin plugin, SocketClient socketClient) {
        this.plugin = plugin;
        this.socketClient = socketClient;
    }


    protected void clientMessageReader(Socket socket, ObjectInputStream in) throws IOException {
        while (!socket.isClosed()) {
            try {
                Object object = in.readObject();
                Packet packet = (Packet) object;

                if (socketClient.isDebug()) {
                    socketClient.getLogger().log(Level.INFO, () -> "Packet received: " + packet.getClassName() + "\n" + packet);
                }

                Bukkit.getScheduler().runTask(plugin, () -> Bukkit.getPluginManager().callEvent(new PacketReceivedEventSpigot(packet)));

            } catch (ClassNotFoundException |
                     InvalidClassException |
                     StreamCorruptedException |
                     OptionalDataException e) {
                if (socketClient.isDebug()) {
                    e.printStackTrace();
                }

            }
        }
    }
}
