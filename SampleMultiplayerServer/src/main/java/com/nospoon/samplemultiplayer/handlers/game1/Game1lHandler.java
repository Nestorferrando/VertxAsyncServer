package com.nospoon.samplemultiplayer.handlers.game1;

import com.nospoon.samplemultiplayer.api.FakeMultiplayerDBApi;
import com.nospoon.samplemultiplayer.handlers.MultiplayerHandler;
import com.nospoon.samplemultiplayer.handlers.common.room.RoomHandler;
import com.nospoon.samplemultiplayer.model.common.room.RoomProperties;
import com.nospoon.vertxserver.core.messagehandlers.MessageHandler;
import com.nospoon.vertxserver.core.model.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nestor on 8/2/2016.
 */
public class Game1lHandler extends MultiplayerHandler {


    private List<Player> playingPlayers = new ArrayList<>();



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

    }
}
