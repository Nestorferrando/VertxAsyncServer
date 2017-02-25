package com.nospoon.vertxserver.core.messagehandlers

import com.nospoon.vertxserver.core.model.Player

/**
 * Created by Nestor on 8/11/2016.
 */
interface PlayerAttacher {


    fun attachPlayers(players: List<Player>)

    fun detachPlayers(players: List<Player>)

    fun attachPlayer(player: Player)

    fun detachPlayer(player: Player)

}
