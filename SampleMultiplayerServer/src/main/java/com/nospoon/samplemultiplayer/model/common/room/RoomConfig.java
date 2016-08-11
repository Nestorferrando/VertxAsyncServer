package com.nospoon.samplemultiplayer.model.common.room;

import com.nospoon.vertxserver.core.messagehandlers.MessageHandler;

/**
 * Created by Nestor on 8/2/2016.
 */
public class RoomConfig<T extends MessageHandler> {

    private String roomName;
    private RoomProperties<T> properties;

    public RoomConfig(String roomName, RoomProperties<T> properties) {
        this.roomName = roomName;
        this.properties = properties;
    }

    public String getRoomName() {
        return roomName;
    }

    public RoomProperties<T> getProperties() {
        return properties;
    }
}
