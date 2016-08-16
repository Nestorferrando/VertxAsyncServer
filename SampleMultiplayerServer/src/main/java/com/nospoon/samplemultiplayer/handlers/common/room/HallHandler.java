package com.nospoon.samplemultiplayer.handlers.common.room;

import com.nospoon.samplemultiplayer.handlers.MultiplayerHandler;
import com.nospoon.samplemultiplayer.model.common.room.HallProperties;
import com.nospoon.vertxserver.core.messagehandlers.HandlerConsumers;
import com.nospoon.vertxserver.core.model.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nestor on 8/2/2016.
 */
public class HallHandler<Q, T extends MultiplayerHandler<Q>> extends MultiplayerHandler<HallProperties<Q, T>> {


    private List<Player> playingPlayers = new ArrayList<>();
    private List<RoomHandler> availableRooms = new ArrayList<>();


    @Override
    protected HandlerConsumers createAttachmentConsumers() {
        return null;
    }

    @Override
    public void onStart() {

        config().getPossibleRoomsProperties().forEach(qtRoomProperties -> {

        });
    }


}
