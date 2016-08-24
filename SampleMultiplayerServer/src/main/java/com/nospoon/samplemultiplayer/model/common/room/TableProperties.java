package com.nospoon.samplemultiplayer.model.common.room;

import com.google.gson.Gson;
import com.nospoon.samplemultiplayer.model.common.game1.Game1TableSelections;

/**
 * Created by Nestor on 8/16/2016.
 */
public abstract class TableProperties<T,Q,S> {

    protected S selections;
    protected Class<S> selectionClass;

    public TableProperties(S selections, Class<S> selectionClass) {
        this.selections = selections;
        this.selectionClass = selectionClass;
    }

    public abstract Class<T> getGameType();

    public abstract Q getGameConfig();


    public String getSerializedSelection() {
        return new Gson().toJson(selections);
    }

    public void setSerializedSelection(String selection) {
        selections = new Gson().fromJson(selection,selectionClass);
    }


    public String getSerialized() {
        return new Gson().toJson(this);
    }

}
