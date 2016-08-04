package com.nospoon.samplemultiplayer.model.common.room;

import com.nospoon.vertxserver.core.messagehandlers.MessageHandler;

/**
 * Created by Nestor on 8/2/2016.
 */
public class GroupProperties<T extends MessageHandler> {

    private String groupID;
    private int maxSize;
    public Class<T> gameClass;

    public GroupProperties(String groupID, int maxSize, Class<T> gameClass) {
        this.groupID = groupID;
        this.maxSize = maxSize;
        this.gameClass = gameClass;
    }

    public String getGroupID() {
        return groupID;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public Class<T> getGameClass() {
        return gameClass;
    }
}
