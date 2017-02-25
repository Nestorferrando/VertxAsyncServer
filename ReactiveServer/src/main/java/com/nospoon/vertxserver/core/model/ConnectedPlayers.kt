package com.nospoon.vertxserver.core.model

import com.nospoon.vertxserver.core.messagehandlers.MessageHandler
import io.vertx.core.net.NetSocket

import java.util.*

/**
 * Created by Nestor on 7/26/2016.
 */
class ConnectedPlayers {

    private val playersInServer: MutableMap<NetSocket, Player>
    private val reverseList: MutableMap<Player, NetSocket>
    private val messagesQueue: MutableMap<Player, MessageQueue>
    private val playerHandlers: MutableMap<Player, AttachedHandlers>

    init {
        playersInServer = HashMap<NetSocket, Player>()
        reverseList = HashMap<Player, NetSocket>()
        playerHandlers = HashMap<Player, AttachedHandlers>()
        messagesQueue = HashMap<Player, MessageQueue>()
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

    fun addPlayer(player: Player, socket: NetSocket) {
        playersInServer.put(socket, player)
        reverseList.put(player, socket)
        playerHandlers.put(player, AttachedHandlers())
        messagesQueue.put(player, MessageQueue())
    }

    fun removePlayer(socket: NetSocket): Player {
        val oldPlayer = playersInServer.remove(socket)!!
        reverseList.remove(oldPlayer)
        messagesQueue.remove(oldPlayer)
        getAssignedHandlers(oldPlayer).getHandlers().forEach { handler -> handler .detachPlayer(oldPlayer) }
        playerHandlers.remove(oldPlayer)
        return oldPlayer
    }

}
