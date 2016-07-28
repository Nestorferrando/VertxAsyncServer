package com.nospoon.vertxserver.core.messagehandlers;

import com.nospoon.vertxserver.core.model.ConnectedPlayers;
import com.nospoon.vertxserver.core.model.Player;

import java.util.List;

/**
 * Created by Nestor on 7/27/2016.
 */
public class MessageSendUtils {

    private ConnectedPlayers connected;

    MessageSendUtils(ConnectedPlayers connected) {
        this.connected = connected;
    }

    public void sendToPlayer(Player player, Object msg)
    {
    //no me apetece acabarlo, pero vamos, esta claro
    }

    public void sendToPlayers(List<Player> players, Object msg)
    {
//no me apetece acabarlo, pero vamos, esta claro
    }

}
