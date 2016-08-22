package com.nospoon.samplemultiplayer.messages.fromserver.hall;

/**
 * Created by Nestor on 22/08/2016.
 */
public class JoinToRoomResponse {

    private boolean success;

    public JoinToRoomResponse(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }
}
