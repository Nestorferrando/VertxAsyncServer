package com.nospoon.samplemultiplayer.model.common.room;

/**
 * Created by Nestor on 8/2/2016.
 */
public class RoomProperties<T extends TableProperties> {

    public String roomID;
    public int maxPlayers;
    public T TableProperties;

    public RoomProperties(String roomID, int maxPlayers,  T tableProperties) {
        this.roomID = roomID;
        this.maxPlayers = maxPlayers;
        TableProperties = tableProperties;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public T getTableProperties() {
        return TableProperties;
    }
}
