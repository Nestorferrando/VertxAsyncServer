package com.nospoon.vertxserver.core.inboxroute;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nospoon.jpromises.Promise;
import com.nospoon.jpromises.Promises;
import com.nospoon.vertxserver.core.model.ConnectedPlayers;
import com.nospoon.vertxserver.core.model.Player;
import com.nospoon.vertxserver.messages.ContainerMessage;
import io.vertx.core.net.NetSocket;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;


/**
 * Created by Nestor on 7/26/2016.
 */
public class MessageRouter {

    private ConnectedPlayers players;
    private Gson gson = new GsonBuilder().create();

    public MessageRouter(ConnectedPlayers players) {
        this.players = players;

    }

    public void enRouteMessage(NetSocket origin, String serializedMsg) {

        try {
            ContainerMessage container = gson.fromJson(serializedMsg, ContainerMessage.class);
            Class c = Class.forName(container.fullyQualifiedMessageName);
            Object msg = gson.fromJson(container.serializedMessage, c);
            Player player = players.getPlayer(origin);
            players.getQueue(player).enqueueHandler((input) -> handleMessageForPlayer(msg, player));

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private Promise<Void> handleMessageForPlayer(Object message, Player player) {


        List<Method> possibleHandlers = possibleHandlers(message, players.getHandlers(player).getHandlerTypes());

        if (possibleHandlers.size() == 0)
            return Promises.reject(new RuntimeException("Not handler found for message " + message.getClass().getName()));

        //we just take first handler for now...


        //un manejador debe poder... mandar mensajes a un player, o una lista de players
        //atachear un nuevo manejador a un jugador
        //llamadas a un api


        return Promises.resolve(null);
    }


    private List<Method> possibleHandlers(Object message, List<Class> handlersTypes) {
        List<Method> callableMethods = new ArrayList<>();
        handlersTypes.forEach(type -> {
            Method[] methods = type.getMethods();
            for (Method method : methods) {
                if (method.getName().equals("on")) {
                    if (method.getParameterTypes()[0].equals(message.getClass())) {
                        callableMethods.add(method);
                    }
                }
            }

        });
        return callableMethods;
    }

}
