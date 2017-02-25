package com.nospoon.vertxserver.core.model

import com.nospoon.vertxserver.core.messagehandlers.MessageHandler

import java.util.HashMap
import java.util.stream.Collectors

/**
 * Created by Nestor on 7/26/2016.
 */
class AttachedHandlers {

    private val handlers: ArrayList<MessageHandler<*, *>> = ArrayList()

    public fun addHandler(handler: MessageHandler<*, *>) {
        handlers.add(handler)
    }

    public fun removeHandler(handlerClass: MessageHandler<*, *>) {
        handlers.remove(handlerClass)
    }

    public fun getHandlers(): List<MessageHandler<*, *>> {
        return handlers
    }

}
