package com.nospoon.vertxserver.core.inboxroute;

import com.nospoon.jpromises.Promise;
import com.nospoon.jpromises.Promises;
import com.nospoon.vertxserver.core.messagehandlers.MessageHandler;
import com.nospoon.vertxserver.core.model.ConnectedPlayers;
import com.nospoon.vertxserver.core.model.Player;
import com.nospoon.vertxserver.messages.MessageUtils;
import io.vertx.core.net.NetSocket;

import java.lang.reflect.Method;
import java.util.List;


/**
 * Created by Nestor on 7/26/2016.
 */
public class MessageRouter {

    private ConnectedPlayers players;

    public MessageRouter(ConnectedPlayers players) {
        this.players = players;

    }

    public void enRouteMessage(NetSocket origin, String serializedMsgs) {

        MessageUtils.deserialize(serializedMsgs).forEach(msg -> {
            Player player = players.getPlayer(origin);
            players.getQueue(player).enqueueHandler((input) -> handleMessageForPlayer(msg, player));
        });
    }

    private Promise<Void> handleMessageForPlayer(Object message, Player player) {

        SuitableHandlerMethod suitableHandler = possibleHandlers(message, players.getAssignedHandlers(player).getHandlers());

        if (suitableHandler == null)
            return Promises.reject(new RuntimeException("Not handler found for message " + message.getClass().getName()));

        //we just take first handler for now...

        try {
            return (Promise<Void>) suitableHandler.getMethod().invoke(suitableHandler.getHandler(), message, player);
        } catch (Exception e) {
            return Promises.reject(new RuntimeException("Error invoking handler:  " + suitableHandler.getMethod().toString() + ", " + e.toString()));
        }
    }


    private SuitableHandlerMethod possibleHandlers(Object message, List<MessageHandler> availableHandlers) {

        for (MessageHandler handler : availableHandlers) {
            Method[] methods = handler.getClass().getMethods();
            for (Method method : methods) {
                if (method.getName().equals("on") && method.getParameterCount() == 2) {
                    if (method.getParameterTypes()[0].equals(message.getClass()) && method.getParameterTypes()[1].equals(Player.class)) {
                        return new SuitableHandlerMethod(handler, method);
                    }
                }
            }
        }
        return null;
    }

    private class SuitableHandlerMethod {
        private Object handler;
        private Method method;

        public SuitableHandlerMethod(Object handler, Method method) {
            this.handler = handler;
            this.method = method;
        }

        public Object getHandler() {
            return handler;
        }

        public Method getMethod() {
            return method;
        }
    }
}



