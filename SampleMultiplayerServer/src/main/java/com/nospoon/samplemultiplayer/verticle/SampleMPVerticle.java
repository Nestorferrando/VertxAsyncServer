package com.nospoon.samplemultiplayer.verticle;

import com.nospoon.samplemultiplayer.api.FakeMultiplayerDBApi;
import com.nospoon.samplemultiplayer.handlers.MultiplayerHandler;
import com.nospoon.samplemultiplayer.handlers.RootHandler;
import com.nospoon.samplemultiplayer.handlers.common.room.HallHandler;
import com.nospoon.samplemultiplayer.model.common.Range;
import com.nospoon.samplemultiplayer.model.game1.Game1TableProperties;
import com.nospoon.samplemultiplayer.model.common.room.HallProperties;
import com.nospoon.samplemultiplayer.model.common.room.RoomProperties;
import com.nospoon.samplemultiplayer.model.common.root.InitialHandlers;
import com.nospoon.vertxserver.core.ServerVerticle;
import com.nospoon.vertxserver.core.model.Player;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        List<RoomProperties<Game1TableProperties>> roomsForGame1 = Arrays.asList(
                new RoomProperties("EasyRoom",50, new Game1TableProperties(new Range(0, 5), new Range(10, 200))),
                new RoomProperties("MediumRoom", 50, new Game1TableProperties(new Range(5, 10), new Range(10, 500))),
                new RoomProperties("HardRoom", 50, new Game1TableProperties(new Range(10, 15), new Range(10, 1000))));

        HallHandler<Game1TableProperties> game1Hall = createHandlerUtils().createHandler(HallHandler.class, new HallProperties(roomsForGame1));

        Map<String, MultiplayerHandler> initialHandlers = new HashMap<>();
        initialHandlers.put("game1Server",game1Hall);

        root = createHandlerUtils().createHandler(RootHandler.class, new InitialHandlers(initialHandlers));
    }
}

