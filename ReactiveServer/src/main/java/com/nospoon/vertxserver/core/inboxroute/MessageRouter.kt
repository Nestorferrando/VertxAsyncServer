package com.nospoon.vertxserver.core.inboxroute

import com.nospoon.jpromises.Promise
import com.nospoon.jpromises.Promises
import com.nospoon.vertxserver.core.messagehandlers.MessageHandler
import com.nospoon.vertxserver.core.model.ConnectedPlayers
import com.nospoon.vertxserver.core.model.Player
import com.nospoon.vertxserver.messages.*
import io.vertx.core.net.NetSocket

import java.lang.reflect.Method


/**
 * Created by Nestor on 7/26/2016.
 */
class MessageRouter(private val players: ConnectedPlayers) {

    fun enRouteMessage(origin: NetSocket, serializedMsgs: String) {

        deserialize(serializedMsgs).forEach { msg ->
            val player = players.getPlayer(origin)
            players.getQueue(player).enqueueHandler { handleMessageForPlayer(msg, player) }
        }
    }

    private fun handleMessageForPlayer(message: Any, player: Player): Promise<Void> {

        val suitableHandler = possibleHandlers(message, players.getAssignedHandlers(player).getHandlers()) ?: return Promises.reject<Void>(RuntimeException("Not handler found for message " + message.javaClass.name))

//we just take first handler for now...

        try {
            return suitableHandler.method.invoke(suitableHandler.handler, message, player) as Promise<Void>
        } catch (e: Exception) {
            return Promises.reject<Void>(RuntimeException("Error invoking handler:  " + suitableHandler.method.toString() + ", " + e.toString()))
        }

    }


    private fun possibleHandlers(message: Any, availableHandlers: List<MessageHandler<*, *>>): SuitableHandlerMethod? {

        for (handler in availableHandlers) {
            val methods = handler::class.java.methods
            methods
                    .filter { it.name == "on" && it.parameterCount == 2 && it.parameterTypes[0] == message::class.java && it.parameterTypes[1] == Player::class.java }
                    .forEach { return SuitableHandlerMethod(handler, it) }
        }
        return null
    }

    private inner class SuitableHandlerMethod(val handler: Any, val method: Method)
}



