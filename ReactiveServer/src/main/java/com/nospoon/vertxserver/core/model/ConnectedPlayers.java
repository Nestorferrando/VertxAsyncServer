package com.nospoon.vertxserver.core.model;

import com.nospoon.vertxserver.core.dbapi.DBApi;
import com.nospoon.vertxserver.core.messagehandlers.HandlerUtils;
import com.nospoon.vertxserver.core.messagehandlers.MessageHandler;
import io.vertx.core.net.NetSocket;

import java.util.*;

/**
 * Created by Nestor on 7/26/2016.
 */
public class ConnectedPlayers {


    private Map<NetSocket, Player> playersInServer;
    private Map<Player, NetSocket> reverseList;
    private Map<Player, MessageQueue> messagesQueue;
    private Map<Player, AttachedHandlers> playerHandlers;


    public ConnectedPlayers() {
        playersInServer = new HashMap<>();
        reverseList = new HashMap<>();
        playerHandlers = new HashMap<>();
        messagesQueue = new HashMap<>();
    }


    public Player getPlayer(NetSocket socket) {
        return playersInServer.get(socket);
    }

    public NetSocket getSocket(Player player) {
        return reverseList.get(player);
    }

    public AttachedHandlers getAssignedHandlers(Player player) {
        return playerHandlers.get(player);
    }
    public MessageQueue getQueue(Player player) {
        return messagesQueue.get(player);
    }

    public void addPlayer(Player player, NetSocket socket) {
        playersInServer.put(socket, player);
        reverseList.put(player, socket);
        playerHandlers.put(player, new AttachedHandlers());
        messagesQueue.put(player, new MessageQueue());
    }

    public Player removePlayer(NetSocket socket) {
        Player oldPlayer = playersInServer.remove(socket);
        reverseList.remove(oldPlayer);
        messagesQueue.remove(oldPlayer);
        List<MessageHandler> handlers = new ArrayList<>(getAssignedHandlers(oldPlayer).getHandlers());
        handlers.forEach(handler -> handler.getAttacher().detachPlayer(oldPlayer));
        playerHandlers.remove(oldPlayer);
        return oldPlayer;
    }

}
