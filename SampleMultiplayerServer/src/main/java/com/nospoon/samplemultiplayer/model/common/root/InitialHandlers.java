package com.nospoon.samplemultiplayer.model.common.root;

import com.nospoon.samplemultiplayer.handlers.MultiplayerHandler;

import java.util.List;
import java.util.Map;

/**
 * Created by Nestor on 8/12/2016.
 */
public class InitialHandlers {


private Map<String,MultiplayerHandler> handlers;

    public InitialHandlers(Map<String,MultiplayerHandler> initialHandlers) {
        this.handlers = initialHandlers;
    }

    public Map<String,MultiplayerHandler> getHandlers() {
        return handlers;
    }
}
