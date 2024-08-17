package me.cubixor.socketsmc.common.packets;

import java.io.Serializable;

public interface Packet extends Serializable {

    default java.lang.String getClassName() {
        return this.getClass().getName();
    }

    String toString();
}
