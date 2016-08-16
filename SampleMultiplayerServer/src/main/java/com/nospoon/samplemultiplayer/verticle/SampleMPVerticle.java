package com.nospoon.samplemultiplayer.verticle;

import com.nospoon.samplemultiplayer.api.FakeMultiplayerDBApi;
import com.nospoon.samplemultiplayer.handlers.MultiplayerHandler;
import com.nospoon.samplemultiplayer.handlers.RootHandler;
import com.nospoon.samplemultiplayer.handlers.common.room.HallHandler;
import com.nospoon.samplemultiplayer.handlers.game1.Game1lHandler;
import com.nospoon.samplemultiplayer.model.common.game1.Game1Config;
import com.nospoon.samplemultiplayer.model.common.room.HallProperties;
import com.nospoon.samplemultiplayer.model.common.room.RoomProperties;
import com.nospoon.samplemultiplayer.model.common.root.InitialHandlers;
import com.nospoon.vertxserver.core.ServerVerticle;
import com.nospoon.vertxserver.core.model.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nestor on 8/12/2016.
 */
public class SampleMPVerticle extends ServerVerticle<FakeMultiplayerDBApi> {

    private RootHandler root = null;

    @Override
    protected FakeMultiplayerDBApi initializeAPI() {
        return new FakeMultiplayerDBApi();
    }


    @Override
    protected void attachRootHandlerToPlayer(Player player) {

        root.getAttacher().attachPlayer(player);


    }


    @Override
    protected void onStart() {
        List<MultiplayerHandler> initialHandlers = new ArrayList<>();

        List<RoomProperties<Game1Config, Game1lHandler>> roomsForGame1 = new ArrayList<>();
        roomsForGame1.add(new RoomProperties(50, 0, Game1lHandler.class));
        roomsForGame1.add(new RoomProperties(100, 0, Game1lHandler.class));
        roomsForGame1.add(new RoomProperties(500, 0, Game1lHandler.class));

        HallHandler<Game1Config, Game1lHandler> game1Hall = createHandlerUtils().createHandler(HallHandler.class, new HallProperties(roomsForGame1));

        initialHandlers.add(game1Hall);

        root = createHandlerUtils().createHandler(RootHandler.class, new InitialHandlers(initialHandlers));
    }
}

