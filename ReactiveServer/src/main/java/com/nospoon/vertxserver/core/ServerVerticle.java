package com.nospoon.vertxserver.core;

import com.nospoon.vertxserver.core.messagehandlers.HandlerUtils;
import com.nospoon.vertxserver.messagehandlers.LoginHandler;
import com.nospoon.vertxserver.core.inboxroute.MessageRouter;
import com.nospoon.vertxserver.core.dbapi.DBApi;
import com.nospoon.vertxserver.core.dbapi.FakeDBApi;
import com.nospoon.vertxserver.core.model.ConnectedPlayers;
import com.nospoon.vertxserver.core.model.Player;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

import java.util.Arrays;
import java.util.UUID;

public class ServerVerticle extends AbstractVerticle {

    private ConnectedPlayers connections;
    private DBApi api;
    private MessageRouter router;

    @Override
    public void start(Future<Void> fut) {

        connections = new ConnectedPlayers();
        api = new FakeDBApi();
        router = new MessageRouter(connections);


        vertx.createNetServer().connectHandler(socket ->
        {
            String sessionID = UUID.randomUUID().toString();
            Player player = new Player(sessionID);
            connections.addPlayer(player, socket);

            //add first handler
            new HandlerUtils(connections,api).createHandlerFor(LoginHandler.class, Arrays.asList(player));


            socket.handler(buffer -> {
                router.enRouteMessage(socket,buffer.getString(0,buffer.length()));

            });

            socket.closeHandler((handler)->{
              connections.removePlayer(socket);
            });

        });
    }
}