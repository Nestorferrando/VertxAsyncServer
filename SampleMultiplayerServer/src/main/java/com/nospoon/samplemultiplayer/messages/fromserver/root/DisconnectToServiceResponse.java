package com.nospoon.samplemultiplayer.messages.fromserver.root;

/**
 * Created by Nestor on 22/08/2016.
 */
public class DisconnectToServiceResponse {


    private boolean successful;

    public DisconnectToServiceResponse(boolean successful) {
        this.successful = successful;
    }

    public boolean isSuccessful() {
        return successful;
    }
}
