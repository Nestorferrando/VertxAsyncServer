package com.nospoon.vertxserver.core.messagehandlers

import com.nospoon.vertxserver.core.dbapi.DBApi
import com.nospoon.vertxserver.core.model.CoreServerManager
import com.nospoon.vertxserver.core.model.Player
import com.nospoon.vertxserver.messages.serialize
import io.vertx.core.buffer.Buffer
import java.util.*

import java.util.function.Consumer

/**
 * Created by Nestor on 7/26/2016.
 */
abstract class MessageHandler<S, T : DBApi>(private val connected: CoreServerManager, protected val dbApi: T, protected val config: S) {

    val ID = UUID.randomUUID().toString()

    public abstract fun attachmentConsumers(): HandlerConsumers

    protected abstract fun onStart()

    protected abstract fun onStop()



    protected fun getAttacher() :PlayerAttacher
    {
        return connected
    }

    protected fun <Q, MH : MessageHandler<Q, T>> createHandler(handlerType: Class<MH>, configObject: Q?): MH {
        val handler = handlerType.constructors.first().newInstance(connected,dbApi,configObject) as MH
        handler.onStart()
        connected.registerHandler(handler)
        return handler
    }

    protected fun destroyHandler(handlerID: String) {
        connected.unregisterHandler(handlerID).onStop()
    }

    protected fun toPlayers(players: List<Player>, msg: Any) {
        val stream = serialize(msg)
        players.forEach { player -> connected.getSocket(player).write(Buffer.buffer(stream)) }
    }

    protected fun toPlayer(player: Player, msg: Any) {
        val stream = serialize(msg)
        connected.getSocket(player).write(Buffer.buffer(stream))
    }


    protected fun toPlayersBut(players: List<Player>, rejectedPlayer: Player, msg: Any) {
        val stream = serialize(msg)
        players.forEach { player -> if (player != rejectedPlayer) connected.getSocket(player).write(Buffer.buffer(stream)) }
    }

}
