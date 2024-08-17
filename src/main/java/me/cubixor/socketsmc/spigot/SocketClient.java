package me.cubixor.socketsmc.spigot;


import me.cubixor.socketsmc.spigot.event.SocketConnectedEventSpigot;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SocketClient {

    private final JavaPlugin plugin;
    private final Logger logger;
    private final boolean debug;

    private final String host;
    private final int port;
    private final String server;

    private final SocketClientSender sender;
    private final SocketClientReceiver receiver;
    private Socket socket;


    public SocketClient(JavaPlugin plugin, String host, int port, String server, boolean debug) {
        this.plugin = plugin;
        this.debug = debug;
        this.logger = Bukkit.getLogger();

        sender = new SocketClientSender(this);
        receiver = new SocketClientReceiver(plugin, this);

        this.host = host;
        this.port = port;
        this.server = server;
    }

    public void clientSetup() {
        new BukkitRunnable() {
            @Override
            public void run() {
                boolean msg = socket == null;

                if (!clientConnect(host, port, server)) {
                    if (msg) {
                        logger.log(Level.WARNING, "§eCouldn't connect to the bungeecord server. Plugin will try to connect until it succeeds.");
                    }

                    tryConnect();
                }
            }
        }.runTaskAsynchronously(plugin);
    }

    private void tryConnect() {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (!clientConnect(host, port, server)) {
                    tryConnect();
                }
            }

        }.runTaskLaterAsynchronously(plugin, 20);
    }

    private boolean clientConnect(String host, int port, String server) {
        try {
            socket = new Socket(host, port);

            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.writeUTF(server);
            out.flush();

            Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> sender.send(socket, out));
            Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> clientReceive(in));

            Bukkit.getScheduler().runTask(plugin, () -> Bukkit.getPluginManager().callEvent(new SocketConnectedEventSpigot()));

            logger.log(Level.INFO, "§aSuccessfully connected to the bungeecord server!");
            return true;
        } catch (IOException ignored) {
            return false;
        }
    }

    private void clientReceive(ObjectInputStream in) {
        try {
            receiver.clientMessageReader(socket, in);
        } catch (IOException e) {
            if (!socket.isClosed()) {
                closeConnections();

                tryConnect();
                logger.log(Level.WARNING, "§eLost connection with the bungeecord server. Trying to reconnect...");

                if (debug) {
                    logger.log(Level.WARNING, "Lost connection with the socket server", e);
                }
            }
        }
    }

    public void closeConnections() {
        try {
            getSender().close();
            socket.close();
        } catch (IOException e) {
            if (debug) {
                logger.log(Level.WARNING, "Error while closing connections", e);
            }
        }
    }

    public Logger getLogger() {
        return logger;
    }

    public boolean isDebug() {
        return debug;
    }

    public SocketClientSender getSender() {
        return sender;
    }
}
