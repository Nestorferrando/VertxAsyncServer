package com.nospoon.samplemultiplayer.handlers.game1;

import com.nospoon.samplemultiplayer.handlers.MultiplayerHandler;
import com.nospoon.samplemultiplayer.model.game1.Game1Config;
import com.nospoon.vertxserver.core.messagehandlers.HandlerConsumers;
import com.nospoon.vertxserver.core.model.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nestor on 8/2/2016.
 */
public class Game1lHandler extends MultiplayerHandler<Game1Config> {


    private List<Player> playingPlayers = new ArrayList<>();


    @Override
    protected HandlerConsumers createAttachmentConsumers() {
        return null;
    }

    @Override
    public void onStart() {

    }
}
