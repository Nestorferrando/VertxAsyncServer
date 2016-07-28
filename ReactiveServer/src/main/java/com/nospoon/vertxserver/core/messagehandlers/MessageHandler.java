package com.nospoon.vertxserver.core.messagehandlers;

import com.nospoon.vertxserver.core.dbapi.DBApi;
import com.nospoon.vertxserver.core.model.Player;

/**
 * Created by Nestor on 7/26/2016.
 */
public abstract class MessageHandler {

    private DBApi dbApi;
    private HandlerUtils handlerManager;
    private MessageSendUtils sendManager;


    MessageHandler initialize(DBApi dbApi, HandlerUtils handlerManager, MessageSendUtils sendManager) {
        this.dbApi = dbApi;
        this.handlerManager = handlerManager;
        this.sendManager = sendManager;
        return this;
    }

    protected DBApi dbApi() {
        return dbApi;
    }

    protected HandlerUtils handlerManager() {
        return handlerManager;
    }

    protected MessageSendUtils sendManager() {
        return sendManager;
    }

    public abstract void playerAttached(Player player);

    public abstract void playerDetached(Player player);

    public abstract void onStart();

}
