package com.nospoon.samplemultiplayer.messages.fromclient;

/**
 * Created by Nestor on 8/2/2016.
 */
public class LoginRequest {

    String userID;
    String session;

    public LoginRequest(String userID, String session) {
        this.userID = userID;
        this.session = session;
    }

    public String getUserID() {
        return userID;
    }

    public String getSession() {
        return session;
    }
}
