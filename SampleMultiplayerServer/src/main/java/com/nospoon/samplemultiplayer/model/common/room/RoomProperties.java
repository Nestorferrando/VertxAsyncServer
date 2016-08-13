package com.nospoon.samplemultiplayer.model.common.room;

import com.nospoon.samplemultiplayer.handlers.MultiplayerHandler;

/**
 * Created by Nestor on 8/2/2016.
 */
public class RoomProperties<Q, T extends MultiplayerHandler<Q>> {

    public int maxPlayers;
    public int roomDifficulty;
    public Class<T> gameClass;


    public RoomProperties(int maxPlayers, int roomDifficulty, Class<T> gameClass) {
        this.maxPlayers = maxPlayers;
        this.roomDifficulty = roomDifficulty;
        this.gameClass = gameClass;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public int getRoomDifficulty() {
        return roomDifficulty;
    }

    public Class<T> getGameHandler() {
        return gameClass;
    }

}
