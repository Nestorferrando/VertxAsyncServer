package com.nospoon.samplemultiplayer.handlers;

import com.nospoon.samplemultiplayer.handlers.common.room.HallHandler;
import com.nospoon.samplemultiplayer.handlers.game2.Game2Handler;
import com.nospoon.vertxserver.core.messagehandlers.HandlerConsumers;
import com.nospoon.vertxserver.core.messagehandlers.MessageHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nestor on 8/1/2016.
 */
public class RootHandler extends MultiplayerHandler<Void> {


    private LoginHandler loginHandler;

    public RootHandler() {


        List<MessageHandler> mainHandlers = new ArrayList<>();
        mainHandlers.add(handlerManager().createHandler(HallHandler.class));
        mainHandlers.add(handlerManager().createHandler(Game2Handler.class));

        loginHandler = handlerManager().createHandler(LoginHandler.class,mainHandlers);

    }


    @Override
    protected HandlerConsumers createAttachmentConsumers() {
        return new HandlerConsumers(
                player -> loginHandler.getAttacher().attachPlayer(player),
                player -> {});
    }

    @Override
    protected void onStart() {
    }

}
