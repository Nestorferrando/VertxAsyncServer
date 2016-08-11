package com.nospoon.vertxserver;

import com.nospoon.vertxserver.api.FakeDBApi;
import com.nospoon.vertxserver.core.ServerVerticle;
import com.nospoon.vertxserver.core.messagehandlers.HandlerUtils;
import com.nospoon.vertxserver.core.model.Player;
import com.nospoon.vertxserver.messagehandlers.PingPongHandler;

/**
 * Created by Nestor on 8/1/2016.
 */
public class PingPongServerVerticle extends ServerVerticle<FakeDBApi> {

    @Override
    protected FakeDBApi initializeAPI() {
        return new FakeDBApi();
    }


    @Override
    protected void attachRootHandlerToPlayer(Player player) {
        PingPongHandler rootHandler =createHandlerUtils().createHandler(PingPongHandler.class);
        rootHandler.getAttacher().attachPlayer(player);
    }
}
