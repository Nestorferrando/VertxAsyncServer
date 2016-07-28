package com.nospoon.vertxserver.core.messagehandlers;

import com.nospoon.vertxserver.core.dbapi.DBApi;
import com.nospoon.vertxserver.core.model.ConnectedPlayers;
import com.nospoon.vertxserver.core.model.Player;
import com.nospoon.vertxserver.messagehandlers.LoginHandler;

import java.util.List;

/**
 * Created by Nestor on 7/27/2016.
 */
public class HandlerUtils {


    private ConnectedPlayers connected;
    private DBApi api;

    public HandlerUtils(ConnectedPlayers connected, DBApi api) {
        this.connected = connected;
        this.api = api;
    }


    public<T extends MessageHandler> T  createHandler(Class<T> handlerType)
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

    public<T extends MessageHandler> T  createHandlerFor(Class<T> handlerType, List<Player> playersToAttach)
    {
        T handler = createHandler(handlerType);
        attachHandlerToPlayers(handler,playersToAttach);
        return handler;
    }


    public void attachHandlerToPlayers(MessageHandler handler, List<Player> players)
    {
        players.forEach(player -> {connected.getHandlers(player).addHandler(handler);handler.playerAttached(player);});
    }

    public void detachHandlerToPlayers(MessageHandler handler, List<Player> players)
    {
        players.forEach(player -> {connected.getHandlers(player).removeHandler(handler.getClass());handler.playerAttached(player);});
    }


}
