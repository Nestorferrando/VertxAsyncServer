package com.nospoon.vertxserver.core.messagehandlers

import com.nospoon.vertxserver.core.model.Player

/**
 * Created by Nestor on 8/11/2016.
 */
interface PlayerAttacher {


    fun attachPlayers(players: List<Player>, handlerID:String)


    fun detachPlayers(players: List<Player>, handlerID:String)


    fun attachPlayer(player: Player, handlerID:String)

    fun detachPlayer(player: Player, handlerID:String)

}
