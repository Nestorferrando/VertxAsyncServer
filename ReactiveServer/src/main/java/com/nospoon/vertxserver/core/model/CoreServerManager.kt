package com.nospoon.vertxserver.core.model

import com.nospoon.vertxserver.core.messagehandlers.MessageHandler
import com.nospoon.vertxserver.core.messagehandlers.PlayerAttacher
import io.vertx.core.net.NetSocket

import java.util.*

/**
 * Created by Nestor on 7/26/2016.
 */
class CoreServerManager : PlayerAttacher {

    private val playersInServer: MutableMap<NetSocket, Player> = HashMap()
    private val reverseList: MutableMap<Player, NetSocket> = HashMap()
    private val messagesQueue: MutableMap<Player, MessageQueue> = HashMap()
    private val playerHandlers: MutableMap<Player, AttachedHandlers> = HashMap()
    private val allHandlers : MutableMap<String, MessageHandler<*,*>> = HashMap()


    fun registerHandler(handler: MessageHandler<*,*>){
        allHandlers[handler.ID]=handler;
    }

    fun unregisterHandler(handlerID: String):MessageHandler<*,*>{
       return allHandlers.remove(handlerID)!!
    }

    fun getPlayer(socket: NetSocket): Player {
        return playersInServer[socket]!!
    }

    fun getSocket(player: Player): NetSocket {
        return reverseList[player]!!
    }

    fun getAssignedHandlers(player: Player): AttachedHandlers {
        return playerHandlers[player]!!
    }

    fun getQueue(player: Player): MessageQueue {
        return messagesQueue[player]!!
    }

    fun sessionStarted(player: Player, socket: NetSocket) {
        playersInServer.put(socket, player)
        reverseList.put(player, socket)
        playerHandlers.put(player, AttachedHandlers())
        messagesQueue.put(player, MessageQueue())
    }

    fun sessionFinished(socket: NetSocket): Player {
        val oldPlayer = playersInServer.remove(socket)!!
        reverseList.remove(oldPlayer)
        messagesQueue.remove(oldPlayer)
        getAssignedHandlers(oldPlayer).getHandlers().forEach { handler -> handler.attachmentConsumers().onPlayerDetached().invoke(oldPlayer)}
        playerHandlers.remove(oldPlayer)
        return oldPlayer
    }


     override fun attachPlayers(players: List<Player>, handlerID:String) {
        players.forEach { attachPlayer(it, handlerID)}
    }

    override fun detachPlayers(players: List<Player>, handlerID:String) {
        players.forEach { detachPlayer(it, handlerID)}
    }

    override fun attachPlayer(player: Player, handlerID:String) {

        var handler = allHandlers[handlerID]!!
        getAssignedHandlers(player).addHandler(handler)
        handler.attachmentConsumers().onPlayerAttached().invoke(player)
    }

    override fun detachPlayer(player: Player, handlerID:String) {

        var handler = allHandlers[handlerID]!!
        getAssignedHandlers(player).removeHandler(handler)
        handler.attachmentConsumers().onPlayerDetached().invoke(player)
    }
}
