package com.nospoon.samplemultiplayer.messages.fromserver.common.room;

/**
 * Created by Nestor on 8/23/2016.
 */
public class GetTablePropertiesResponse {

    private String serializedProperties;

    public GetTablePropertiesResponse(String serialized) {
        this.serializedProperties = serialized;
    }

    public String getSerializedProperties() {
        return serializedProperties;
    }
}
