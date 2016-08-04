package com.nospoon.samplemultiplayer.model.common.room;

import com.nospoon.vertxserver.core.messagehandlers.MessageHandler;

/**
 * Created by Nestor on 8/2/2016.
 */
public class RoomProperties<T extends MessageHandler> {

    public int maxPlayers;
    public String roomName;
    public int roomDifficulty;
    public Class<T> gameClass;


    public RoomProperties(int maxPlayers, String roomName, int roomDifficulty,Class<T> gameClass) {
        this.maxPlayers = maxPlayers;
        this.roomName = roomName;
        this.roomDifficulty = roomDifficulty;
        this.gameClass = gameClass;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public String getRoomName() {
        return roomName;
    }

    public int getRoomDifficulty() {
        return roomDifficulty;
    }

    public Class<T> getGameHandler()
    {
        return gameClass;
    }

}
