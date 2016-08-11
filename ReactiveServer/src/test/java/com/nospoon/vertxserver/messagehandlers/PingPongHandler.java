package com.nospoon.vertxserver.messagehandlers;

import com.nospoon.jpromises.Promise;
import com.nospoon.jpromises.Promises;
import com.nospoon.vertxserver.api.FakeDBApi;
import com.nospoon.vertxserver.core.messagehandlers.HandlerConsumers;
import com.nospoon.vertxserver.core.messagehandlers.MessageHandler;
import com.nospoon.vertxserver.core.model.Player;
import com.nospoon.vertxserver.messages.fromclient.Ping;
import com.nospoon.vertxserver.messages.fromserver.Pong;

import java.util.function.Consumer;

/**
 * Created by Nestor on 7/26/2016.
 */
public class PingPongHandler extends MessageHandler<Void,FakeDBApi> {


    public Promise<Void> on(Ping request, Player player) {

        System.out.println("Net server received: " + request.toString());
        sendManager().sendToPlayer(player, new Pong("Me cago en tus muertos"));
        return Promises.resolve(null);
    }


    @Override
    protected HandlerConsumers createAttachmentConsumers() {
        return new HandlerConsumers(player ->{},player ->{});
    }

    @Override
    protected void onStart() {
    }
}
