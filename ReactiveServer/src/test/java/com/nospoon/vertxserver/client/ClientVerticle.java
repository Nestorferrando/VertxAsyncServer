package com.nospoon.vertxserver.client;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

public class ClientVerticle extends AbstractVerticle {


    @Override
    public void start(Future<Void> fut) {

        vertx.createNetClient().connect(10003, "localhost", res -> {
            fut.complete();
            if (res.succeeded()) {
                SampleClient client = new SampleClient(10000, res.result());
                client.performSession().then((a) -> vertx.eventBus().send("CLIENT_FINISHED", null));
            } else {
                System.err.println("Connection error!!");
                fut.fail("Connection error");
            }
        });


    }
}