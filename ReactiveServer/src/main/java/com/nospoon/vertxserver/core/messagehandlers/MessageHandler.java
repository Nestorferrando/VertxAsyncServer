package com.nospoon.vertxserver.core.messagehandlers;

import com.nospoon.vertxserver.core.dbapi.DBApi;

import java.util.function.Consumer;

/**
 * Created by Nestor on 7/26/2016.
 */
public abstract class MessageHandler<S, T extends DBApi> {

    private S config;
    private T dbApi;
    private HandlerUtils<T> handlerManager;
    private MessageSendUtils sendManager;

    MessageHandler<S, T> initialize(HandlerUtils handlerManager, MessageSendUtils sendManager, T dbApi, S config) {
        this.dbApi = dbApi;
        this.config = config;
        this.handlerManager = handlerManager;
        this.sendManager = sendManager;
        return this;
    }

    public PlayerAttacher getAttacher() {
        return handlerManager;
    }

    protected S config() {
        return config;
    }

    protected T dbApi() {
        return dbApi;
    }

    protected HandlerUtils<T> handlerManager() {
        return handlerManager;
    }

    protected MessageSendUtils send() {
        return sendManager;
    }

    protected abstract HandlerConsumers createAttachmentConsumers();

    protected abstract void onStart();

}
