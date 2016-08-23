package com.nospoon.samplemultiplayer.handlers;

import com.nospoon.jpromises.Promise;
import com.nospoon.samplemultiplayer.messages.fromclient.LoginRequest;
import com.nospoon.samplemultiplayer.messages.fromserver.LoginResponse;
import com.nospoon.vertxserver.core.messagehandlers.HandlerConsumers;
import com.nospoon.vertxserver.core.model.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by Nestor on 8/1/2016.
 */
public class LoginHandler extends MultiplayerHandler<Void> {


    private List<Consumer<Player>> loginSuccess;
    private List<Consumer<Player>> loginFailure;

    public LoginHandler() {
        loginSuccess = new ArrayList<>();
        loginFailure = new ArrayList<>();
    }
    public void AddSuccessLoginHandler( Consumer<Player> consumer)
    {
        this.loginSuccess.add(consumer);
    }

    public void AddFailLoginHandler( Consumer<Player> consumer)
    {
        this.loginFailure.add(consumer);
    }

    public Promise<Void> on(LoginRequest request, Player player) {

        return dbApi().loginRequest(request.getUserID(), request.getSession())
                .then(result -> {
                    if (result == Boolean.TRUE) {
                        loginSuccess.forEach(playerConsumer -> playerConsumer.accept(player));
                    }
                    else{
                        loginFailure.forEach(playerConsumer -> playerConsumer.accept(player));
                    }
                    send().toPlayer(player, new LoginResponse(result));
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
