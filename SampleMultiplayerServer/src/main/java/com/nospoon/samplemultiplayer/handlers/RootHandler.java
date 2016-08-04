package com.nospoon.samplemultiplayer.handlers;

import com.nospoon.samplemultiplayer.api.FakeMultiplayerDBApi;
import com.nospoon.samplemultiplayer.handlers.game1.Game1HallHandler;
import com.nospoon.samplemultiplayer.handlers.game2.Game2Handler;
import com.nospoon.vertxserver.core.messagehandlers.MessageHandler;
import com.nospoon.vertxserver.core.model.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nestor on 8/1/2016.
 */
public class RootHandler extends MessageHandler<FakeMultiplayerDBApi> {


    private LoginHandler loginHandler;

    public RootHandler() {


        List<MessageHandler> mainHandlers = new ArrayList<>();
        mainHandlers.add(handlerManager().createHandler(Game1HallHandler.class));
        mainHandlers.add(handlerManager().createHandler(Game2Handler.class));

        loginHandler = handlerManager().createHandler(LoginHandler.class);
        loginHandler.setHandlersAvailableWhenLogin(mainHandlers);

    }

    @Override
    public void playerAttached(Player player) {

        handlerManager().attachHandlerToPlayer(loginHandler,player);

    }

    @Override
    public void playerDetached(Player player) {

    }

    @Override
    public void onStart() {

    }
}
