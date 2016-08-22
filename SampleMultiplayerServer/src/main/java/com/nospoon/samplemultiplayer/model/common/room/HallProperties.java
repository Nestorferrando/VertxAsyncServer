package com.nospoon.samplemultiplayer.model.common.room;

import java.util.List;

/**
 * Created by Nestor on 8/2/2016.
 */
public class HallProperties<T extends TableProperties> {


    private List<RoomProperties<T>> properties;

    public HallProperties(List<RoomProperties<T>> properties) {
        this.properties = properties;
    }

    public List<RoomProperties<T>> getProperties() {
        return properties;
    }

}
