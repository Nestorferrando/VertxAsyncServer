package com.nospoon.samplemultiplayer.model.common.room;

import java.util.List;

/**
 * Created by Nestor on 8/2/2016.
 */
public class HallProperties<T extends TableProperties> {

    private String hallID;
    private List<RoomProperties<T>> properties;

    public HallProperties(String hallID, List<RoomProperties<T>> properties) {
        this.hallID = hallID;
        this.properties = properties;
    }

    public List<RoomProperties<T>> getProperties() {
        return properties;
    }

    public String getHallID() {
        return hallID;
    }
}
