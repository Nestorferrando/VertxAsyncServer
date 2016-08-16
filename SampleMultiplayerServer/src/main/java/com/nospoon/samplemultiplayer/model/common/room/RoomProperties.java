package com.nospoon.samplemultiplayer.model.common.room;

/**
 * Created by Nestor on 8/2/2016.
 */
public class RoomProperties<T extends TableProperties> {

    public int maxPlayers;
    public int roomDifficulty;
    public T TableProperties;

    public RoomProperties(int maxPlayers, int roomDifficulty, T tableProperties) {
        this.maxPlayers = maxPlayers;
        this.roomDifficulty = roomDifficulty;
        TableProperties = tableProperties;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public int getRoomDifficulty() {
        return roomDifficulty;
    }

    public T getTableProperties() {
        return TableProperties;
    }
}
