package com.nospoon.vertxserver.core.messagehandlers

import com.nospoon.vertxserver.core.model.Player

import java.util.function.Consumer

/**
 * Created by Nestor on 8/11/2016.
 */
class HandlerConsumers(private val onPlayerAttached: Consumer<Player>, private val onPlayerDetached: Consumer<Player>) {

    internal fun onPlayerAttached(): Consumer<Player> {
        return onPlayerAttached
    }

    internal fun onPlayerDetached(): Consumer<Player> {
        return onPlayerDetached
    }
}
