package com.nospoon.vertxserver.messagehandlers;

import com.nospoon.jpromises.Promise;
import com.nospoon.jpromises.Promises;
import com.nospoon.vertxserver.api.FakeDBApi;
import com.nospoon.vertxserver.core.messagehandlers.HandlerConsumers;
import com.nospoon.vertxserver.core.messagehandlers.MessageHandler;
import com.nospoon.vertxserver.core.model.CoreServerManager;
import com.nospoon.vertxserver.core.model.Player;
import com.nospoon.vertxserver.messages.fromclient.Ping;
import com.nospoon.vertxserver.messages.fromserver.Pong;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Nestor on 7/26/2016.
 */
public class PingPongHandler extends MessageHandler<Void,FakeDBApi> {


    public PingPongHandler(@NotNull CoreServerManager connected, @NotNull FakeDBApi dbApi, Void config) {
        super(connected, dbApi, config);
    }

    public Promise<Void> on(Ping request, Player player) {

        System.out.println("Net server received: " + request.toString());
         toPlayer(player, new Pong("Me cago en tus muertos"));
        return Promises.resolve(null);
    }



    @Override
    protected void onStart() {
    }

    @NotNull
    @Override
    protected HandlerConsumers attachmentConsumers() {
        return new HandlerConsumers(player ->{},player ->{});
    }
}
