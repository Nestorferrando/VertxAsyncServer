package com.nospoon.samplemultiplayer.handlers;

import com.nospoon.samplemultiplayer.api.FakeMultiplayerDBApi;
import com.nospoon.vertxserver.core.messagehandlers.MessageHandler;

/**
 * Created by Nestor on 8/4/2016.
 */
public abstract class MultiplayerHandler<T> extends MessageHandler<T,FakeMultiplayerDBApi> {
}
