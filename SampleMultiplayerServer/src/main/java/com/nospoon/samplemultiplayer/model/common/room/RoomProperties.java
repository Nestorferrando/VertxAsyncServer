package com.nospoon.samplemultiplayer.model.common.room;

import com.nospoon.vertxserver.core.messagehandlers.MessageHandler;

/**
 * Created by Nestor on 8/2/2016.
 */
public class RoomProperties<T extends MessageHandler> {

    public int maxPlayers;
    public int roomDifficulty;
    public Class<T> gameClass;


    public RoomProperties(int maxPlayers, int roomDifficulty,Class<T> gameClass) {
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

    public Class<T> getGameHandler()
    {
        return gameClass;
    }

}
