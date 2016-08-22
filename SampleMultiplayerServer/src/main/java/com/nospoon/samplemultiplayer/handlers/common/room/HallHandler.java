package com.nospoon.samplemultiplayer.handlers.common.room;

import com.nospoon.samplemultiplayer.handlers.MultiplayerHandler;
import com.nospoon.samplemultiplayer.model.common.room.HallProperties;
import com.nospoon.samplemultiplayer.model.common.room.TableProperties;
import com.nospoon.vertxserver.core.messagehandlers.HandlerConsumers;
import com.nospoon.vertxserver.core.model.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nestor on 8/2/2016.
 */
public class HallHandler<Q extends TableProperties> extends MultiplayerHandler<HallProperties<Q>> {

    private List<Player> playingPlayers = new ArrayList<>();
    private List<RoomHandler> availableRooms = new ArrayList<>();


    @Override
    protected HandlerConsumers createAttachmentConsumers() {
        return new HandlerConsumers(player -> {
            playingPlayers.add(player);
        }, player -> {
            if (playingPlayers.contains(player)) disconnectPlayer(player);
        });
    }

    private void disconnectPlayer(Player player) {
        playingPlayers.remove(player);
    }


    @Override
    public void onStart() {

        //we create static rooms. Dynamic rooms on demand maybe in the future?
        config().getProperties().forEach(roomProperty -> {
            RoomHandler<Q> roomHandler = handlerManager().createHandler(RoomHandler.class, roomProperty);
            availableRooms.add(roomHandler);

        });

    }


}
