package com.nospoon.vertxserver.core.messagehandlers;

import com.google.gson.Gson;
import com.nospoon.vertxserver.core.model.ConnectedPlayers;
import com.nospoon.vertxserver.core.model.Player;
import com.nospoon.vertxserver.messages.MessageUtils;
import io.vertx.core.buffer.Buffer;

import java.util.List;

/**
 * Created by Nestor on 7/27/2016.
 */
public class MessageSendUtils {

    private ConnectedPlayers connected;

    MessageSendUtils(ConnectedPlayers connected) {
        this.connected = connected;
    }

    public void sendToPlayer(Player player, Object msg) {

        connected.getSocket(player).write(Buffer.buffer(MessageUtils.serialize(msg)));

    }

    public void sendToPlayers(List<Player> players, Object msg) {
        String stream =MessageUtils.serialize(msg);
        players.forEach(player-> connected.getSocket(player).write(Buffer.buffer(stream)));
    }


    public void sendToPlayersBut(List<Player> players, Player rejectedPlayer, Object msg) {
        String stream =MessageUtils.serialize(msg);
        players.forEach(player->{if (!player.equals(rejectedPlayer))connected.getSocket(player).write(Buffer.buffer(stream));});
    }


}
