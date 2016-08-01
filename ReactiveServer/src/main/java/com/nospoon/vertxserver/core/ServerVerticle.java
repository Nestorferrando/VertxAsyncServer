package com.nospoon.vertxserver.core;

import com.nospoon.vertxserver.core.dbapi.DBApi;
import com.nospoon.vertxserver.core.inboxroute.MessageRouter;
import com.nospoon.vertxserver.core.messagehandlers.HandlerUtils;
import com.nospoon.vertxserver.core.model.ConnectedPlayers;
import com.nospoon.vertxserver.core.model.Player;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.UUID;

public abstract class ServerVerticle<T extends DBApi> extends AbstractVerticle {

    private Logger logger = LogManager.getLogger();

    private ConnectedPlayers connections;
    private T api;
    private MessageRouter router;

    @Override
    public void start(Future<Void> fut) {

        connections = new ConnectedPlayers();
        api = initializeAPI();
        router = new MessageRouter(connections);

        vertx.createNetServer().connectHandler(socket ->
        {
            String sessionID = UUID.randomUUID().toString();
            Player player = new Player(sessionID);
            connections.addPlayer(player, socket);

            logger.info("Connecion established, ip " + socket.remoteAddress());
            //add first handler
            getHandlerUtils().createHandlerFor(getRootHandler(), Arrays.asList(player));

            socket.handler(buffer -> router.enRouteMessage(socket, buffer.getString(0, buffer.length())));
            socket.closeHandler((handler) -> connections.removePlayer(socket));

            socket.exceptionHandler((error) -> {
                logger.error("Socket error " + error.toString());
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

    private HandlerUtils<T> getHandlerUtils() {
        return new HandlerUtils<>(connections,api);
    }

    protected abstract T initializeAPI();

    protected abstract Class getRootHandler();

}