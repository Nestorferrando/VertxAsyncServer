package com.nospoon.samplemultiplayer.handlers.game2;

import com.nospoon.samplemultiplayer.api.FakeMultiplayerDBApi;
import com.nospoon.vertxserver.core.messagehandlers.MessageHandler;
import com.nospoon.vertxserver.core.model.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nestor on 8/2/2016.
 */
public class Game2Handler extends MessageHandler<FakeMultiplayerDBApi> {

    private List<Player> playingPlayer= new ArrayList<>();




    @Override
    public void playerAttached(Player player) {
        playingPlayer.add(player);
    }

    @Override
    public void playerDetached(Player player) {
        playingPlayer.remove(player);
    }
    @Override
    public void onStart() {

    }
}
