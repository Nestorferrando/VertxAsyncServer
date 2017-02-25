package com.nospoon.vertxserver.core;

import com.nospoon.vertxserver.core.dbapi.DBApi;
import com.nospoon.vertxserver.core.inboxroute.MessageRouter;
import com.nospoon.vertxserver.core.model.CoreServerManager;
import com.nospoon.vertxserver.core.model.Player;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.UUID;

public abstract class ServerVerticle<T extends DBApi> extends AbstractVerticle {

    private Logger logger = LogManager.getLogger();

    protected CoreServerManager connections;
    protected T api;
    protected MessageRouter router;

    @Override
    public void start(Future<Void> fut) {

        connections = new CoreServerManager();
        api = initializeAPI();
        router = new MessageRouter(connections);

        vertx.createNetServer().connectHandler(socket ->
        {
            String sessionID = UUID.randomUUID().toString();
            Player player = new Player(sessionID);
            connections.sessionStarted(player, socket);

            logger.info("Connecion established, ip " + socket.remoteAddress());

            //add first handler
            attachRootHandlerToPlayer(player);

            socket.handler(buffer -> router.enRouteMessage(socket, buffer.getString(0, buffer.length())));
            socket.closeHandler((handler) -> connections.sessionFinished(socket));

            socket.exceptionHandler((error) -> {
                logger.error("Socket error " + error.toString());
            });

        })
                .listen(10003, result -> {
                    if (result.succeeded()) {
                        onStart();
                        fut.complete();
                    } else {
                        fut.fail(result.cause());
                    }
                });
    }

    protected abstract T initializeAPI();

    protected abstract void onStart();

    protected abstract void attachRootHandlerToPlayer(Player player);

}