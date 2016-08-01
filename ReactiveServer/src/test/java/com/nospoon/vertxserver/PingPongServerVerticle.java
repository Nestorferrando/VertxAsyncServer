package com.nospoon.vertxserver;

import com.nospoon.vertxserver.api.FakeDBApi;
import com.nospoon.vertxserver.core.ServerVerticle;
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
    protected Class getRootHandler() {
        return PingPongHandler.class;
    }
}
