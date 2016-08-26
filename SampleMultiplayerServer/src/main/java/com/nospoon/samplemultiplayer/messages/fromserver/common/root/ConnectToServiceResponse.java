package com.nospoon.samplemultiplayer.messages.fromserver.common.root;

/**
 * Created by Nestor on 22/08/2016.
 */
public class ConnectToServiceResponse {


    private boolean successful;

    public ConnectToServiceResponse(boolean successful) {
        this.successful = successful;
    }

    public boolean isSuccessful() {
        return successful;
    }
}
