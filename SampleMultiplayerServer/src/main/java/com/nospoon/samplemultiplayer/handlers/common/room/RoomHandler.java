package com.nospoon.samplemultiplayer.handlers.common.room;

import com.nospoon.samplemultiplayer.handlers.MultiplayerHandler;
import com.nospoon.samplemultiplayer.model.common.room.RoomProperties;
import com.nospoon.vertxserver.core.messagehandlers.HandlerConsumers;
import com.nospoon.vertxserver.core.model.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nestor on 8/2/2016.
 */
public class RoomHandler<Q, T extends MultiplayerHandler<Q>> extends MultiplayerHandler<RoomProperties<Q,T>> {


    private List<Player> playersInRoom = new ArrayList<>();


    @Override
    protected HandlerConsumers createAttachmentConsumers() {
        return new HandlerConsumers(player ->playersInRoom.add(player),player->playersInRoom.remove(player) );
    }


    public boolean isRoomFull() {
        return playersInRoom.size() >= config().maxPlayers;
    }

    @Override
    public void onStart() {

    }
}
