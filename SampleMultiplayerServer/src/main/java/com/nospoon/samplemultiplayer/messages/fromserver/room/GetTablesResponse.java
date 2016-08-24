package com.nospoon.samplemultiplayer.messages.fromserver.room;

import java.util.List;

/**
 * Created by Nestor on 8/23/2016.
 */
public class GetTablesResponse {


    private final List<String> tableIDs;
    private final List<String> tableSelectiosn;
    private final List<List<String>> tablePlayers;

    public GetTablesResponse(List<String> tableIDs, List<String> tableSelections, List<List<String>> tablePlayers) {
        this.tableIDs = tableIDs;
        this.tableSelectiosn = tableSelections;
        this.tablePlayers = tablePlayers;
    }


    public List<String> getTableIDs() {
        return tableIDs;
    }

    public List<String> getTableSelectiosn() {
        return tableSelectiosn;
    }

    public List<List<String>> getTablePlayers() {
        return tablePlayers;
    }
}
