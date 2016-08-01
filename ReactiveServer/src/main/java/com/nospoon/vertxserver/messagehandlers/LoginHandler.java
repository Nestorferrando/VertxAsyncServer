package com.nospoon.vertxserver.messagehandlers;

import com.nospoon.jpromises.Promise;
import com.nospoon.jpromises.Promises;
import com.nospoon.vertxserver.core.messagehandlers.MessageHandler;
import com.nospoon.vertxserver.core.model.Player;
import com.nospoon.vertxserver.messages.fromclient.Ping;
import com.nospoon.vertxserver.messages.fromserver.Pong;

/**
 * Created by Nestor on 7/26/2016.
 */
public class LoginHandler extends MessageHandler {


    public Promise<Void> on(Ping request, Player player) {

        System.out.println("Net server received: " + request.toString());
        sendManager().sendToPlayer(player, new Pong("Me cago en tus muertos"));
        return Promises.resolve(null);
    }


    @Override
    public void playerDetached(Player player) {

    }


    @Override
    public void playerAttached(Player player) {

    }

    @Override
    public void onStart() {

    }
}
