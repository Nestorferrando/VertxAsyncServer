package com.nospoon;

import com.google.gson.Gson;
import com.nospoon.client.SampleClient;
import com.nospoon.vertxserver.core.ServerVerticle;
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
        vertx.deployVerticle(ServerVerticle.class.getName(),
                context.asyncAssertSuccess());
    }

    @After
    public void tearDown(TestContext context) {
        vertx.close(context.asyncAssertSuccess());
    }

    @Test
    public void testMyApplication(TestContext context) {
        final Async async = context.async();


        vertx.createNetClient().connect(10003, "localhost", res -> {
            if (res.succeeded()) {
                SampleClient client = new SampleClient(2500, res.result());
                client.performSession().then((a) -> async.complete());
            } else {
                System.err.println("Connection error!!");
                async.complete();
            }
        });
    }
}