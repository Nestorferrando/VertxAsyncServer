package com.nospoon.samplemultiplayer.handlers.common.room;

import com.nospoon.jpromises.Promise;
import com.nospoon.jpromises.Promises;
import com.nospoon.samplemultiplayer.handlers.MultiplayerHandler;
import com.nospoon.samplemultiplayer.messages.fromclient.hall.GetRoomsRequest;
import com.nospoon.samplemultiplayer.messages.fromclient.hall.JoinToRoomRequest;
import com.nospoon.samplemultiplayer.messages.fromserver.hall.GetRoomsResponse;
import com.nospoon.samplemultiplayer.messages.fromserver.hall.JoinToRoomResponse;
import com.nospoon.samplemultiplayer.model.common.room.HallProperties;
import com.nospoon.samplemultiplayer.model.common.room.TableProperties;
import com.nospoon.vertxserver.core.messagehandlers.HandlerConsumers;
import com.nospoon.vertxserver.core.model.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Nestor on 8/2/2016.
 */
public class HallHandler<Q extends TableProperties> extends MultiplayerHandler<HallProperties<Q>> {

    private List<Player> playingPlayers = new ArrayList<>();
    private List<RoomHandler> availableRooms = new ArrayList<>();


    @Override
    protected HandlerConsumers createAttachmentConsumers() {
        return new HandlerConsumers(player -> {
            playingPlayers.add(player);
        }, player -> {
            if (playingPlayers.contains(player)) disconnectPlayer(player);
        });
    }


    public Promise<Void> on(GetRoomsRequest request, Player player) {

        send().toPlayer(player, new GetRoomsResponse(
                availableRooms.stream().map(room -> room.getID()).collect(Collectors.toList()),
                availableRooms.stream().map(room -> room.getMaxPlayers()).collect(Collectors.toList()),
                availableRooms.stream().map(room -> room.getPlayersCount()).collect(Collectors.toList())));

        return Promises.resolve();
    }

    public Promise<Void> on(JoinToRoomRequest request, Player player) {

        Optional<RoomHandler> roomHandler = availableRooms.stream().filter(room -> room.getID().equals(request.getRoomID())).findFirst();
        if (roomHandler.isPresent() && !roomHandler.get().isRoomFull()) {
            roomHandler.get().getAttacher().attachPlayer(player);
            send().toPlayer(player, new JoinToRoomResponse(true));
        }
        else {
            send().toPlayer(player, new JoinToRoomResponse(false));
        }

        return Promises.resolve();
    }


    private void disconnectPlayer(Player player) {
        playingPlayers.remove(player);
    }


    @Override
    public void onStart() {

        //we create static rooms. Dynamic rooms on demand maybe in the future?
        config().getProperties().forEach(roomProperty -> {
            RoomHandler<Q> roomHandler = handlerManager().createHandler(RoomHandler.class, roomProperty);
            availableRooms.add(roomHandler);

        });

    }


}
