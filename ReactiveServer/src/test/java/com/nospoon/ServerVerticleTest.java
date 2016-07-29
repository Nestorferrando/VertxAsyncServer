package com.nospoon;

import com.google.gson.Gson;
import com.nospoon.vertxserver.core.ServerVerticle;
import com.nospoon.vertxserver.messages.ContainerMessage;
import com.nospoon.vertxserver.messages.fromclient.Ping;
import io.vertx.core.AsyncResult;
import io.vertx.core.Vertx;
import io.vertx.core.net.NetSocket;
import io.vertx.core.Handler;
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



        vertx.createNetClient().connect(10003,"localhost", res ->{
if (res.succeeded())
{

    res.result().handler(buffer -> {
        System.out.println("Net client receiving: " + buffer.toString("UTF-8")+"\n");
    });

    res.result().write( gson.toJson(new ContainerMessage(Ping.class.getCanonicalName(),gson.toJson(new Ping("k te jodan")))));
    res.result().write( gson.toJson(new ContainerMessage(Ping.class.getCanonicalName(),gson.toJson(new Ping("holaktal")))));
    res.result().write( gson.toJson(new ContainerMessage(Ping.class.getCanonicalName(),gson.toJson(new Ping("holaktal")))));
    res.result().write( gson.toJson(new ContainerMessage(Ping.class.getCanonicalName(),gson.toJson(new Ping("holaktal")))));


}
else{
    System.err.println("Connection error!!");
    async.complete();
}

        });





    }
}