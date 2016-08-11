package com.nospoon.vertxserver.core.messagehandlers;

import com.nospoon.vertxserver.core.dbapi.DBApi;
import com.nospoon.vertxserver.core.model.ConnectedPlayers;
import com.nospoon.vertxserver.core.model.Player;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Nestor on 7/27/2016.
 */
public class HandlerUtils<S extends DBApi> implements PlayerAttacher {

    protected ConnectedPlayers connected;
    private S api;
    private MessageHandler attachedHandler;
    private HandlerConsumers attachedConsumers;

    public HandlerUtils(ConnectedPlayers connected, S api) {
        this.connected = connected;
        this.api = api;
    }

    private void setConsumers(HandlerConsumers consumer) {
        this.attachedConsumers = consumer;
    }

    public <Q, T extends MessageHandler<Q, S>> T createHandler(Class<T> handlerType, Q configObject) {
        try {
            T handler = handlerType.newInstance();
            HandlerUtils<S> newUtils = new HandlerUtils(connected, api);
            newUtils.attachedHandler = handler.initialize(newUtils, new MessageSendUtils(connected), api, configObject);
            newUtils.setConsumers(newUtils.attachedHandler.createAttachmentConsumers());
            handler.onStart();
            return handler;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public <Q, T extends MessageHandler<Q, S>> T createHandler(Class<T> handlerType) {
        return createHandler(handlerType, null);
    }


    public void attachPlayers(List<Player> players) {
        players.forEach(player -> {
            connected.getAssignedHandlers(player).addHandler(attachedHandler);
            attachedConsumers.onPlayerAttached().accept(player);
        });
    }

    public void detachPlayers(List<Player> players) {
        players.forEach(player -> {
            connected.getAssignedHandlers(player).removeHandler(attachedHandler.getClass());
            attachedConsumers.onPlayerDetached().accept(player);
        });
    }

    public void attachPlayer(Player player) {
        attachPlayers(Arrays.asList(player));
    }

    public void detachPlayer(Player player) {
        detachPlayers(Arrays.asList(player));
    }
}
