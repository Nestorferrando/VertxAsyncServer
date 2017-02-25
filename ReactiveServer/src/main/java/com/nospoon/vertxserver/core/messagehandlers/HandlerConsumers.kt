package com.nospoon.vertxserver.core.messagehandlers

import com.nospoon.vertxserver.core.model.Player

import java.util.function.Consumer

/**
 * Created by Nestor on 8/11/2016.
 */
class HandlerConsumers(private val onPlayerAttached: (Player)->Unit, private val onPlayerDetached: (Player)->Unit) {

    internal fun onPlayerAttached(): (Player)->Unit {
        return onPlayerAttached
    }

    internal fun onPlayerDetached(): (Player)->Unit {
        return onPlayerDetached
    }
}
