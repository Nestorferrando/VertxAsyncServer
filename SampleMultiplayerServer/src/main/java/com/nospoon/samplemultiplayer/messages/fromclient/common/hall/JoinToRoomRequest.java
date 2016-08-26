package com.nospoon.samplemultiplayer.messages.fromclient.common.hall;

/**
 * Created by Nestor on 22/08/2016.
 */
public class JoinToRoomRequest {

    private String roomID;


    public JoinToRoomRequest(String roomID) {
        this.roomID = roomID;
    }

    public String getRoomID() {
        return roomID;
    }
}
