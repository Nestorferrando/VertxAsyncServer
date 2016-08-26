package com.nospoon.samplemultiplayer.messages.fromserver.table;

import java.util.List;

/**
 * Created by Nestor on 8/26/2016.
 */
public class TableStateUpdate {

    List<String> players;

    public TableStateUpdate(List<String> players) {
        this.players = players;
    }

    public List<String> getPlayers() {
        return players;
    }
}
