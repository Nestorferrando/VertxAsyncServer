package com.nospoon.samplemultiplayer.handlers;

import com.nospoon.jpromises.Promise;
import com.nospoon.samplemultiplayer.api.FakeMultiplayerDBApi;
import com.nospoon.samplemultiplayer.messages.fromclient.LoginRequest;
import com.nospoon.samplemultiplayer.messages.fromserver.LoginResponse;
import com.nospoon.vertxserver.core.messagehandlers.MessageHandler;
import com.nospoon.vertxserver.core.model.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nestor on 8/1/2016.
 */
public class LoginHandler extends MessageHandler<FakeMultiplayerDBApi> {


    private List<MessageHandler> handlersAvailableWhenLogin;

    public LoginHandler() {
        handlersAvailableWhenLogin = new ArrayList<>();
    }

    public Promise<Void> on(LoginRequest request, Player player) {

        return dbApi().loginRequest(request.getUserID(), request.getSession())
                .then(result -> {
                    if (result == Boolean.TRUE) {
                        handlersAvailableWhenLogin.forEach(handler -> {
                            handlerManager().attachHandlerToPlayer(handler, player);
                        });
                    }
                    sendManager().sendToPlayer(player, new LoginResponse(result));
                }).thenMap(result -> null);

    }

    public void setHandlersAvailableWhenLogin(List<MessageHandler> handlersAvailableWhenLogin) {
        this.handlersAvailableWhenLogin = handlersAvailableWhenLogin;
    }


    @Override
    public void playerAttached(Player player) {

    }

    @Override
    public void playerDetached(Player player) {

    }

    @Override
    public void onStart() {

    }
}
