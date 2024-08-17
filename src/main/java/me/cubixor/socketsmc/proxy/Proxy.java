package me.cubixor.socketsmc.proxy;

public interface Proxy {

    void runAsync(Runnable task);
    void fireEvent(ProxyEvent event);
}
