package com.nospoon.samplemultiplayer.messages.fromclient.root;

/**
 * Created by Nestor on 22/08/2016.
 */
public class ConnectToServiceRequest {

    private String serviceID;

    public ConnectToServiceRequest(String serviceID) {
        this.serviceID = serviceID;
    }

    public String getServiceID() {
        return serviceID;
    }
}
