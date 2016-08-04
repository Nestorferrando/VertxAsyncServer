package com.nospoon.samplemultiplayer.messages.fromserver;

/**
 * Created by Nestor on 8/2/2016.
 */
public class LoginResponse {

    private  Boolean result;

    public LoginResponse(Boolean result) {
        this.result = result;
    }

    public Boolean getResult() {
        return result;
    }
}
