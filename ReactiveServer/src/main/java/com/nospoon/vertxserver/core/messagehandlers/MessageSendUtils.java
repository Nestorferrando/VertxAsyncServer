package com.nospoon.vertxserver.core.messagehandlers;

import com.google.gson.Gson;
import com.nospoon.vertxserver.core.model.ConnectedPlayers;
import com.nospoon.vertxserver.core.model.Player;
import com.nospoon.vertxserver.messages.ContainerMessage;

import java.util.List;

/**
 * Created by Nestor on 7/27/2016.
 */
public class MessageSendUtils {

    private ConnectedPlayers connected;
    private Gson gson;

    MessageSendUtils(ConnectedPlayers connected) {
        this.connected = connected;
        gson = new Gson();
    }

    public void sendToPlayer(Player player, Object msg) {
        ContainerMessage container = createContainer(msg);
        connected.getSocket(player).write(gson.toJson(container));
    }

    public void sendToPlayers(List<Player> players, Object msg) {
        ContainerMessage container = createContainer(msg);
        players.forEach(player-> connected.getSocket(player).write(gson.toJson(container)));
    }

    private ContainerMessage createContainer(Object msg) {
        String name = msg.getClass().getCanonicalName();
        return new ContainerMessage(name, gson.toJson(msg));
    }

}
