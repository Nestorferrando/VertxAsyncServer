package com.nospoon.samplemultiplayer.handlers;

import com.nospoon.samplemultiplayer.model.common.root.InitialHandlers;
import com.nospoon.vertxserver.core.messagehandlers.HandlerConsumers;

/**
 * Created by Nestor on 8/1/2016.
 */
public class RootHandler extends MultiplayerHandler<InitialHandlers> {


    private LoginHandler loginHandler;


    public RootHandler() {

        loginHandler = handlerManager().createHandler(LoginHandler.class);

        loginHandler.AddSuccessLoginHandler(player ->
                config().getHandlers().forEach(multiplayerHandler ->
                        multiplayerHandler.getAttacher().attachPlayer(player)));

    }


    @Override
    protected HandlerConsumers createAttachmentConsumers() {
        return new HandlerConsumers(
                player -> loginHandler.getAttacher().attachPlayer(player),
                player -> {
                });
    }

    @Override
    protected void onStart() {
    }

}
