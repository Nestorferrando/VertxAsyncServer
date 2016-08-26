package com.nospoon.samplemultiplayer.messages.fromserver.table;

/**
 * Created by Nestor on 8/26/2016.
 */
public class JoinTableResponse {

    boolean success;;

    public JoinTableResponse(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }
}
