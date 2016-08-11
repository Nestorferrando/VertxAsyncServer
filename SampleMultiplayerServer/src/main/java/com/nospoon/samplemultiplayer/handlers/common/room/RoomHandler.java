package com.nospoon.samplemultiplayer.handlers.common.room;

import com.nospoon.samplemultiplayer.handlers.MultiplayerHandler;
import com.nospoon.samplemultiplayer.model.common.room.RoomProperties;
import com.nospoon.vertxserver.core.model.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nestor on 8/2/2016.
 */
public class RoomHandler extends MultiplayerHandler {


    private RoomProperties props;
    private List<Player> playersInRoom = new ArrayList<>();


    @Override
    public void playerAttached(Player player) {
        playersInRoom.add(player);
    }

    @Override
    public void playerDetached(Player player) {
        playersInRoom.remove(player);
    }


    public RoomHandler setProps(RoomProperties props) {
        this.props = props;
        return this;
    }

    public boolean isRoomFull() {
        return playersInRoom.size() >= props.maxPlayers;
    }

    @Override
    public void onStart() {

    }
}
