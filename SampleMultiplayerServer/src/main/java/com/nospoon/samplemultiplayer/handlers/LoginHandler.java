package com.nospoon.samplemultiplayer.handlers;

import com.nospoon.jpromises.Promise;
import com.nospoon.samplemultiplayer.api.FakeMultiplayerDBApi;
import com.nospoon.samplemultiplayer.messages.fromclient.LoginRequest;
import com.nospoon.samplemultiplayer.messages.fromserver.LoginResponse;
import com.nospoon.vertxserver.core.messagehandlers.HandlerConsumers;
import com.nospoon.vertxserver.core.messagehandlers.MessageHandler;
import com.nospoon.vertxserver.core.model.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nestor on 8/1/2016.
 */
public class LoginHandler extends MultiplayerHandler<List<MessageHandler>> {


    public LoginHandler() {
    }

    public Promise<Void> on(LoginRequest request, Player player) {

        return dbApi().loginRequest(request.getUserID(), request.getSession())
                .then(result -> {
                    if (result == Boolean.TRUE) {
                        config().forEach(handler -> {
                            handler.getAttacher().attachPlayer(player);
                        });
                    }
                    sendManager().sendToPlayer(player, new LoginResponse(result));
                }).thenMap(result -> null);
    }



    @Override
    protected HandlerConsumers createAttachmentConsumers() {
        return new HandlerConsumers(player->{},player->{});
    }

    @Override
    public void onStart() {

    }
}
