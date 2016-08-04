package com.nospoon.vertxserver.messages.fromserver;

/**
 * Created by Nestor on 7/29/2016.
 */
public class Pong {

    String pingComment;

    public Pong(String pingComment) {
        this.pingComment = pingComment;
    }

    public String getPingComment() {
        return pingComment;
    }

    @Override
    public String toString() {
        return "Pong{" +
                "pingComment='" + pingComment + '\'' +
                '}';
    }
}


