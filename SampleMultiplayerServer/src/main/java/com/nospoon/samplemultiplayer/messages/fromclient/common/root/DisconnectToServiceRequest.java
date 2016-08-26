package com.nospoon.samplemultiplayer.messages.fromclient.common.root;

/**
 * Created by Nestor on 22/08/2016.
 */
public class DisconnectToServiceRequest {

    private String serviceID;

    public DisconnectToServiceRequest(String serviceID) {
        this.serviceID = serviceID;
    }

    public String getServiceID() {
        return serviceID;
    }
}
