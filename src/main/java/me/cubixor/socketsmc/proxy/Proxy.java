package me.cubixor.socketsmc.proxy;

public interface Proxy {

    CommonLogger getLogger();

    EventFactory getEventFactory();

    void runAsync(Runnable task);

    void fireEvent(ProxyEvent event);
}
