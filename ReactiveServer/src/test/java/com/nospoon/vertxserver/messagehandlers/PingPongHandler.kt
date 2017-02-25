package com.nospoon.vertxserver.messagehandlers

import com.nospoon.jpromises.Promise
import com.nospoon.jpromises.Promises
import com.nospoon.vertxserver.api.FakeDBApi
import com.nospoon.vertxserver.core.messagehandlers.HandlerConsumers
import com.nospoon.vertxserver.core.messagehandlers.MessageHandler
import com.nospoon.vertxserver.core.model.CoreServerManager
import com.nospoon.vertxserver.core.model.Player
import com.nospoon.vertxserver.messages.fromclient.Ping
import com.nospoon.vertxserver.messages.fromserver.Pong

/**
 * Created by Nestor on 7/26/2016.
 */
class PingPongHandler(connected: CoreServerManager, dbApi: FakeDBApi, config: PingPongConfig) : MessageHandler<PingPongConfig, FakeDBApi>(connected, dbApi, config) {

    fun on(request: Ping, player: Player): Promise<Void> {

        println("Net server received: " + request.toString())
        toPlayer(player, Pong("Me cago en tus muertos"))
        return Promises.resolve<Void>(null)
    }

    override fun onStop() {

    }

    override fun onStart() {}

    override fun attachmentConsumers(): HandlerConsumers {
        return HandlerConsumers({}, {})
    }
}

class PingPongConfig
