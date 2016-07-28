package com.nospoon.vertxserver.core.model;

import io.vertx.core.net.NetSocket;

import java.util.HashMap;
import java.util.Map;

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

    public AttachedHandlers getHandlers(Player player) {
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
        AttachedHandlers handlers = playerHandlers.remove(oldPlayer);
        handlers.getHandlerTypes().forEach(handlerClass-> handlers.removeHandler(handlerClass).playerDetached(oldPlayer));
        return oldPlayer;
    }

}
