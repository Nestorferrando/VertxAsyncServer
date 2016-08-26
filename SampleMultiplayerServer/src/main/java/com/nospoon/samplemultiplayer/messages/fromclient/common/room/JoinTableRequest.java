package com.nospoon.samplemultiplayer.messages.fromclient.common.room;

/**
 * Created by Nestor on 8/23/2016.
 */
public class JoinTableRequest {
    private String ID;

    public JoinTableRequest(String ID) {
        this.ID = ID;
    }

    public String getID() {
        return ID;
    }
}
