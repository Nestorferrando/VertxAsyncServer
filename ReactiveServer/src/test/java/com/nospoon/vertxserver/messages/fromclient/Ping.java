package com.nospoon.vertxserver.messages.fromclient;

/**
 * Created by Nestor on 7/29/2016.
 */
public class Ping {

    String pingComment;

    public Ping(String pingComment) {
        this.pingComment = pingComment;
    }

    public String getPingComment() {
        return pingComment;
    }

    @Override
    public String toString() {
        return "Ping{" +
                "pingComment='" + pingComment + '\'' +
                '}';
    }
}


