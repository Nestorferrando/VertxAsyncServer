package com.nospoon.samplemultiplayer.model.common.room;

import com.nospoon.vertxserver.core.messagehandlers.MessageHandler;

import java.util.List;

/**
 * Created by Nestor on 8/2/2016.
 */
public class HallProperties<T extends MessageHandler> {

  private  List<RoomProperties<T>> possibleRoomsProperties;

    public HallProperties(List<RoomProperties<T>> possibleRoomsProperties) {
        this.possibleRoomsProperties = possibleRoomsProperties;
    }

    public List<RoomProperties<T>> getPossibleRoomsProperties() {
        return possibleRoomsProperties;
    }
}
