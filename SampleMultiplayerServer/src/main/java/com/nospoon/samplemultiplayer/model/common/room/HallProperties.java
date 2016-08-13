package com.nospoon.samplemultiplayer.model.common.room;

import com.nospoon.samplemultiplayer.handlers.MultiplayerHandler;
import com.nospoon.vertxserver.core.messagehandlers.MessageHandler;

import java.util.List;

/**
 * Created by Nestor on 8/2/2016.
 */
public class HallProperties<Q,T extends MultiplayerHandler<Q>> {

  private  List<RoomProperties<Q,T>> possibleRoomsProperties;

    public HallProperties(List<RoomProperties<Q,T>> possibleRoomsProperties) {
        this.possibleRoomsProperties = possibleRoomsProperties;
    }

    public List<RoomProperties<Q,T>> getPossibleRoomsProperties() {
        return possibleRoomsProperties;
    }
}
