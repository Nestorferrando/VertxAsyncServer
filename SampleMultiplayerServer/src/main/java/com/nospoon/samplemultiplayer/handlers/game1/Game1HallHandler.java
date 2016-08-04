package com.nospoon.samplemultiplayer.handlers.game1;

import com.nospoon.samplemultiplayer.api.FakeMultiplayerDBApi;
import com.nospoon.samplemultiplayer.handlers.common.room.RoomHandler;
import com.nospoon.samplemultiplayer.model.common.room.RoomProperties;
import com.nospoon.vertxserver.core.messagehandlers.MessageHandler;
import com.nospoon.vertxserver.core.model.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nestor on 8/2/2016.
 */
public class Game1HallHandler extends MessageHandler<FakeMultiplayerDBApi> {


    private List<Player> playingPlayers = new ArrayList<>();
    private List<RoomHandler> availableRooms = new ArrayList<>();


    @Override
    public void playerAttached(Player player) {
        playingPlayers.add(player);
    }

    @Override
    public void playerDetached(Player player) {
        playingPlayers.remove(player);
    }

    @Override
    public void onStart() {
        availableRooms.add(new RoomHandler().setProps(new RoomProperties(50,"Easy Room",1,Game1lHandler.class)));
        availableRooms.add(new RoomHandler().setProps(new RoomProperties(50,"Hard Room",10,Game1lHandler.class)));
    }
}
