package com.nospoon.samplemultiplayer.messages.fromserver.room;

/**
 * Created by Nestor on 8/23/2016.
 */
public class JoinTableResponse {

    private boolean result;

    public JoinTableResponse(boolean result) {
        this.result = result;
    }

    public boolean isResult() {
        return result;
    }
}
