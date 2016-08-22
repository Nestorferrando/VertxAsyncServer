package com.nospoon.samplemultiplayer.handlers;

import com.nospoon.jpromises.Promise;
import com.nospoon.jpromises.Promises;
import com.nospoon.samplemultiplayer.messages.fromclient.root.ConnectToServiceRequest;
import com.nospoon.samplemultiplayer.messages.fromclient.root.DisconnectToServiceRequest;
import com.nospoon.samplemultiplayer.messages.fromserver.root.ConnectToServiceResponse;
import com.nospoon.samplemultiplayer.messages.fromserver.root.DisconnectToServiceResponse;
import com.nospoon.samplemultiplayer.model.common.root.InitialHandlers;
import com.nospoon.vertxserver.core.messagehandlers.HandlerConsumers;
import com.nospoon.vertxserver.core.model.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nestor on 8/1/2016.
 */
public class RootHandler extends MultiplayerHandler<InitialHandlers> {


    private LoginHandler loginHandler;
    private List<Player> loggedPlayers;


    public RootHandler() {

        loginHandler = handlerManager().createHandler(LoginHandler.class);
        loggedPlayers = new ArrayList<>();

        loginHandler.AddSuccessLoginHandler(player ->
                        loggedPlayers.add(player)
                /*
                config().getHandlers().forEach(multiplayerHandler ->
                        multiplayerHandler.getAttacher().attachPlayer(player))
        */
        );

    }


    public Promise<Void> on(ConnectToServiceRequest request, Player player) {
        if (!loggedPlayers.contains(player)) return Promises.resolve(null);
        boolean validService=config().getHandlers().containsKey(request.getServiceID());

        if (validService) {
            config().getHandlers().get(request.getServiceID()).getAttacher().attachPlayer(player);
        }

        sendManager().sendToPlayer(player,new ConnectToServiceResponse(validService));
        return Promises.resolve(null);
    }

    public Promise<Void> on(DisconnectToServiceRequest request, Player player) {
        if (!loggedPlayers.contains(player)) return Promises.resolve(null);
        boolean validService=config().getHandlers().containsKey(request.getServiceID());

        if (validService) {
            config().getHandlers().get(request.getServiceID()).getAttacher().detachPlayer(player);
        }

        sendManager().sendToPlayer(player,new DisconnectToServiceResponse(validService));
        return Promises.resolve(null);
    }




    @Override
    protected HandlerConsumers createAttachmentConsumers() {
        return new HandlerConsumers(
                player -> loginHandler.getAttacher().attachPlayer(player),
                player -> { if (loggedPlayers.contains(player)) loggedPlayers.remove(player);
                });
    }

    @Override
    protected void onStart() {
    }

}
