package com.nospoon.samplemultiplayer.model.common.room;

import com.nospoon.samplemultiplayer.handlers.MultiplayerHandler;
import com.nospoon.vertxserver.core.messagehandlers.MessageHandler;

/**
 * Created by Nestor on 8/2/2016.
 */
public class RoomConfig<Q,T extends MultiplayerHandler<Q>> {

    private String roomName;
    private RoomProperties<Q,T> properties;

    public RoomConfig(String roomName, RoomProperties<Q,T> properties) {
        this.roomName = roomName;
        this.properties = properties;
    }

    public String getRoomName() {
        return roomName;
    }

    public RoomProperties<Q,T> getProperties() {
        return properties;
    }
}
