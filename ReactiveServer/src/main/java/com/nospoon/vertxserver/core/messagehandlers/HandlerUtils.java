package com.nospoon.vertxserver.core.messagehandlers;

import com.nospoon.vertxserver.core.dbapi.DBApi;
import com.nospoon.vertxserver.core.model.ConnectedPlayers;
import com.nospoon.vertxserver.core.model.Player;

import java.util.List;

/**
 * Created by Nestor on 7/27/2016.
 */
public class HandlerUtils<S extends DBApi> {


    private ConnectedPlayers connected;
    private S api;

    public HandlerUtils(ConnectedPlayers connected, S api) {
        this.connected = connected;
        this.api = api;
    }


    public<T extends MessageHandler<S>> T  createHandler(Class<T> handlerType)
    {
        try {
            T handler = handlerType.newInstance();
            handler.initialize(api,this, new MessageSendUtils(connected));
            handler.onStart();
            return handler;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public<T extends MessageHandler<S>> T  createHandlerFor(Class<T> handlerType, List<Player> playersToAttach)
    {
        T handler = createHandler(handlerType);
        attachHandlerToPlayers(handler,playersToAttach);
        return handler;
    }


    public void attachHandlerToPlayers(MessageHandler handler, List<Player> players)
    {
        players.forEach(player -> {connected.getAssignedHandlers(player).addHandler(handler);handler.playerAttached(player);});
    }

    public void detachHandlerToPlayers(MessageHandler handler, List<Player> players)
    {
        players.forEach(player -> {connected.getAssignedHandlers(player).removeHandler(handler.getClass());handler.playerAttached(player);});
    }


}
