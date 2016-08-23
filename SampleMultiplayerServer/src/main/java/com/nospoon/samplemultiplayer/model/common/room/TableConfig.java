package com.nospoon.samplemultiplayer.model.common.room;

/**
 * Created by Nestor on 8/22/2016.
 */
public class TableConfig<T extends TableProperties> {

    private T tableProperties;
    private String tableID;

    public TableConfig(T tableProperties, String tableID) {
        this.tableProperties = tableProperties;
        this.tableID = tableID;
    }

    public T getTableProperties() {
        return tableProperties;
    }

    public String getTableID() {
        return tableID;
    }
}
