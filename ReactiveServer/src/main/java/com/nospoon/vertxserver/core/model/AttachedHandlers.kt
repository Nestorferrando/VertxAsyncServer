package com.nospoon.vertxserver.core.model

import com.nospoon.vertxserver.core.messagehandlers.MessageHandler

import java.util.HashMap
import java.util.stream.Collectors

/**
 * Created by Nestor on 7/26/2016.
 */
class AttachedHandlers {

    private val handlers: MutableMap<Class<*>, MessageHandler<*, *>>

    init {
        handlers = HashMap<Class<*>, MessageHandler<*, *>>()
    }

    public fun addHandler(handler: MessageHandler<*, *>) {
        handlers.put(handler::class.java, handler)
    }

    public fun removeHandler(handlerClass: Class<*>) {
        handlers.remove(handlerClass)
    }

    val handlerTypes: List<Class<*>>
        get() = handlers.keys.toList()

    public fun getHandlers(): List<MessageHandler<*, *>> {
        return handlers.values.toList()
    }

}
