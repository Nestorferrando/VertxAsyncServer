package com.nospoon.samplemultiplayer.model.common.room;

import java.util.function.Consumer;

/**
 * Created by Nestor on 8/22/2016.
 */
public class TableConfig<T extends TableProperties> {

    private T tableProperties;
    private String tableID;
    private Consumer<String> onTableFinished;

    public TableConfig(T tableProperties, String tableID, Consumer<String> onTableFinished) {
        this.tableProperties = tableProperties;
        this.tableID = tableID;
        this.onTableFinished = onTableFinished;
    }

    public T getTableProperties() {
        return tableProperties;
    }

    public String getTableID() {
        return tableID;
    }

    public Consumer<String> getOnTableFinished() {
        return onTableFinished;
    }
}
