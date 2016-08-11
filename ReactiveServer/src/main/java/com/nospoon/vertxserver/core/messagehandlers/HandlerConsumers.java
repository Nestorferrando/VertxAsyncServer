package com.nospoon.vertxserver.core.messagehandlers;

import com.nospoon.vertxserver.core.model.Player;

import java.util.function.Consumer;

/**
 * Created by Nestor on 8/11/2016.
 */
public class HandlerConsumers {

    private Consumer<Player> onPlayerAttached;
    private Consumer<Player> onPlayerDetached;


    public HandlerConsumers(Consumer<Player> onPlayerAttached, Consumer<Player> onPlayerDetached) {
        this.onPlayerAttached = onPlayerAttached;
        this.onPlayerDetached = onPlayerDetached;
    }

    final Consumer<Player> onPlayerAttached() {
        return onPlayerAttached;
    }

    final Consumer<Player> onPlayerDetached() {
        return onPlayerDetached;
    }
}
