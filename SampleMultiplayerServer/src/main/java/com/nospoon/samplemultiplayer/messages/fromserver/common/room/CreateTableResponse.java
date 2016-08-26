package com.nospoon.samplemultiplayer.messages.fromserver.common.room;

/**
 * Created by Nestor on 8/23/2016.
 */
public class CreateTableResponse {

    private final boolean result;
    private final String tableID;

    public CreateTableResponse(boolean result, String tableID) {
        this.result = result;
        this.tableID = tableID;
    }

    public boolean isResult() {
        return result;
    }

    public String getTableID() {
        return tableID;
    }
}
