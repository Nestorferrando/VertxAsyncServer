package com.nospoon.vertxserver.core.messagehandlers

import com.nospoon.vertxserver.core.dbapi.DBApi
import com.nospoon.vertxserver.core.model.ConnectedPlayers
import com.nospoon.vertxserver.core.model.Player
import com.nospoon.vertxserver.messages.serialize
import io.vertx.core.buffer.Buffer

import java.util.function.Consumer

/**
 * Created by Nestor on 7/26/2016.
 */
abstract class MessageHandler<S, T : DBApi>(protected val connected: ConnectedPlayers, protected val dbApi: T, protected val config: S) {


    protected abstract fun attachmentConsumers(): HandlerConsumers

    protected abstract fun onStart()


    protected fun <Q, MH : MessageHandler<Q, T>> createHandler(handlerType: Class<MH>, configObject: Q?): MH {
        val handler = handlerType.constructors.first().newInstance(connected,dbApi,configObject) as MH
        handler.onStart()
        return handler
    }



    public fun attachPlayers(players: List<Player>) {
        players.forEach { attachPlayer(it)}
    }

    public fun detachPlayers(players: List<Player>) {
        players.forEach { detachPlayer(it)}
    }

    public fun attachPlayer(player: Player) {
            connected.getAssignedHandlers(player).addHandler(this)
            attachmentConsumers().onPlayerAttached().accept(player)
    }

    public fun detachPlayer(player: Player) {

            connected.getAssignedHandlers(player).removeHandler(this::class.java)
            attachmentConsumers().onPlayerDetached().accept(player)
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
