package com.nospoon;

import com.google.gson.Gson;
import com.nospoon.vertxserver.PingPongServerVerticle;
import com.nospoon.vertxserver.client.ClientVerticle;
import io.vertx.core.Vertx;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(VertxUnitRunner.class)
public class ServerVerticleTest {


    private Vertx vertx;
    private Gson gson = new Gson();

    @Before
    public void setUp(TestContext context) {
        vertx = Vertx.vertx();
        vertx.deployVerticle(PingPongServerVerticle.class.getName(),
                context.asyncAssertSuccess());
    }

    @After
    public void tearDown(TestContext context) {
        vertx.close(context.asyncAssertSuccess());
    }

    @Test
    public void testMyApplication(TestContext context) {
        final Async async = context.async();

        vertx.eventBus().consumer("CLIENT_FINISHED", (result) -> async.complete());

        vertx.deployVerticle(ClientVerticle.class.getName());


    }
}