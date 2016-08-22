package com.nospoon.samplemultiplayer.model.common.room;

/**
 * Created by Nestor on 8/2/2016.
 */
public class RoomProperties<T extends TableProperties> {

    public int maxPlayers;
    public T TableProperties;

    public RoomProperties(int maxPlayers,  T tableProperties) {
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
