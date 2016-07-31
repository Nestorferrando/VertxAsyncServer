package com.nospoon.vertxserver.core;

import com.nospoon.vertxserver.core.dbapi.DBApi;
import com.nospoon.vertxserver.core.dbapi.FakeDBApi;
import com.nospoon.vertxserver.core.inboxroute.MessageRouter;
import com.nospoon.vertxserver.core.messagehandlers.HandlerUtils;
import com.nospoon.vertxserver.core.model.ConnectedPlayers;
import com.nospoon.vertxserver.core.model.Player;
import com.nospoon.vertxserver.messagehandlers.LoginHandler;
import com.nospoon.vertxserver.messages.MessageUtils;
import com.nospoon.vertxserver.messages.fromserver.Pong;
import io.netty.handler.logging.LogLevel;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.net.NetServerOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.UUID;

public class ServerVerticle extends AbstractVerticle {

    private Logger logger = LogManager.getLogger();

    private ConnectedPlayers connections;
    private DBApi api;
    private MessageRouter router;

    @Override
    public void start(Future<Void> fut) {

        connections = new ConnectedPlayers();
        api = new FakeDBApi();
        router = new MessageRouter(connections);

        NetServerOptions options = new NetServerOptions();

        vertx.createNetServer(options).connectHandler(socket ->
        {
            String sessionID = UUID.randomUUID().toString();
            Player player = new Player(sessionID);
            connections.addPlayer(player, socket);

            logger.info("Connecion established, ip "+socket.remoteAddress());
            //add first handler
            new HandlerUtils(connections, api).createHandlerFor(LoginHandler.class, Arrays.asList(player));


            socket.handler(buffer -> {
                //router.enRouteMessage(socket, buffer.getString(0, buffer.length()));
                socket.write(MessageUtils.serialize(new Pong("que te jodan cabronazo hijo de puta que te jodan cabronazo hijo de puta que te jodan cabronazo hijo de puta")));

            });

            socket.closeHandler((handler) -> {

                System.out.println("Socket cerrado");
                connections.removePlayer(socket);

            });


            socket.exceptionHandler((error) -> {
                System.out.println("Socket error");
            });

        })
                .listen(10003, result -> {
                    if (result.succeeded()) {
                        fut.complete();
                    } else {
                        fut.fail(result.cause());
                    }
                });
    }
}