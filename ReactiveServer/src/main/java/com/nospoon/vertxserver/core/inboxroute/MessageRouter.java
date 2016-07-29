package com.nospoon.vertxserver.core.inboxroute;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nospoon.jpromises.Promise;
import com.nospoon.jpromises.Promises;
import com.nospoon.vertxserver.core.messagehandlers.MessageHandler;
import com.nospoon.vertxserver.core.model.ConnectedPlayers;
import com.nospoon.vertxserver.core.model.Player;
import com.nospoon.vertxserver.messages.ContainerMessage;
import io.vertx.core.net.NetSocket;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Nestor on 7/26/2016.
 */
public class MessageRouter {

    private ConnectedPlayers players;
    private Gson gson = new GsonBuilder().create();

    public MessageRouter(ConnectedPlayers players) {
        this.players = players;

    }

    public void enRouteMessage(NetSocket origin, String serializedMsgs) {

        splitBufferIntoJSONS(serializedMsgs).forEach(serializedMsg -> {
            try {
                ContainerMessage container = gson.fromJson(serializedMsg, ContainerMessage.class);
                Class c = Class.forName(container.getFullyQualifiedMessageName());
                Object msg = gson.fromJson(container.getSerializedMessage(), c);
                Player player = players.getPlayer(origin);
                players.getQueue(player).enqueueHandler((input) -> handleMessageForPlayer(msg, player));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });


    }

    private List<String> splitBufferIntoJSONS(String buffer) {
        List<String> splitted = new ArrayList<>();
        String bufferTemp = buffer;
        int tokenIndex = bufferTemp.indexOf("}{");
        while (tokenIndex > -1) {
            splitted.add(bufferTemp.substring(0, tokenIndex + 1));
            bufferTemp = bufferTemp.substring(tokenIndex + 1, bufferTemp.length());
            tokenIndex = bufferTemp.indexOf("}{");
        }
        splitted.add(bufferTemp);
        return splitted;
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



