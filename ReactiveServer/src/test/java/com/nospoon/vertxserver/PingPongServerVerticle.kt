package com.nospoon.vertxserver

import com.nospoon.vertxserver.api.FakeDBApi
import com.nospoon.vertxserver.core.ServerVerticle
import com.nospoon.vertxserver.core.model.Player
import com.nospoon.vertxserver.messagehandlers.PingPongHandler


/**
 * Created by Nestor on 8/1/2016.
 */
class PingPongServerVerticle : ServerVerticle<FakeDBApi>() {


    var rootHandler: PingPongHandler? = null;

    override fun initializeAPI(): FakeDBApi {
        return FakeDBApi()
    }


    override fun attachRootHandlerToPlayer(player: Player) {

        rootHandler?.attachPlayer(player)
    }

    override fun onStart() {
        rootHandler = PingPongHandler(connections, api, null)
    }
}
