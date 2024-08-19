package me.cubixor.socketsmc.proxy;


import me.cubixor.socketsmc.common.SocketConnection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

public class SocketServer {

    private final Proxy proxy;
    private final CommonLogger logger;
    private final boolean debug;

    private final Map<String, SocketConnection> spigotSocket = new HashMap<>();
    private final SocketServerSender sender;
    private final SocketServerReceiver receiver;
    private ServerSocket serverSocket;

    public SocketServer(Proxy proxy, int port, boolean debug) {
        this.proxy = proxy;
        this.logger = proxy.getLogger();
        this.debug = debug;

        sender = new SocketServerSender(this);
        receiver = new SocketServerReceiver(this);

        serverSetup(port);
    }

    private void serverSetup(int port) {
        proxy.runAsync(() -> {
            try {
                serverSocket = new ServerSocket(port);

                proxy.runAsync(() -> sender.send(serverSocket));

                logger.log(Level.INFO, "§aSuccessfully started the socket server!");

                acceptConnection(serverSocket);
            } catch (IOException e) {
                if (debug) {
                    logger.log(Level.WARNING, "Failed to start the socket server", e);
                }
            }
        });
    }

    private void acceptConnection(ServerSocket serverSocket) {
        try {
            Socket socket = serverSocket.accept();
            if (debug) {
                logger.log(Level.INFO, "Accepted connection from client " + socket.getInetAddress());
            }

            proxy.runAsync(() -> acceptConnection(serverSocket));

            clientSetup(socket);
        } catch (IOException e) {
            if (debug) {
                logger.log(Level.WARNING, "Failed to connect to client", e);
            }
        }
    }

    private void clientSetup(Socket socket) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        String server = objectInputStream.readUTF();

        SocketConnection socketConnection = new SocketConnection(socket, objectOutputStream);
        spigotSocket.put(server, socketConnection);

        proxy.fireEvent(proxy.getEventFactory().createSocketConnectedEvent(server));

        logger.log(Level.INFO, "§aSuccessfully connected to the " + server + " server!");

        serverReceive(server, objectInputStream);
    }

    private void serverReceive(String server, ObjectInputStream in) {
        try {
            receiver.serverMessageReader(serverSocket, server, in);
        } catch (IOException e) {
            logger.log(Level.WARNING, "§eDisconnected from the " + server + " server!");

            spigotSocket.remove(server);
            proxy.fireEvent(proxy.getEventFactory().createSocketDisconnectedEvent(server));

            if (debug) {
                logger.log(Level.WARNING, "Disconnected from the client", e);
            }
        }
    }

    public void closeConnections() {
        try {
            serverSocket.close();
            for (SocketConnection socketConnection : spigotSocket.values()) {
                socketConnection.getSocket().close();
            }
        } catch (IOException e) {
            if (debug) {
                e.printStackTrace();
            }
        }
    }

    public CommonLogger getLogger() {
        return logger;
    }

    public boolean isDebug() {
        return debug;
    }

    public SocketConnection getSpigotSocket(String server) {
        return spigotSocket.get(server);
    }

    public Map<String, SocketConnection> getSpigotSockets() {
        return spigotSocket;
    }

    public SocketServerSender getSender() {
        return sender;
    }

    public Proxy getProxy() {
        return proxy;
    }
}