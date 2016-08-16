package com.nospoon.samplemultiplayer.model.common.root;

import com.nospoon.samplemultiplayer.handlers.MultiplayerHandler;

import java.util.List;

/**
 * Created by Nestor on 8/12/2016.
 */
public class InitialHandlers {

private List<MultiplayerHandler> handlers;

    public InitialHandlers(List<MultiplayerHandler> initialHandlers) {
        this.handlers = initialHandlers;
    }

    public List<MultiplayerHandler> getHandlers() {
        return handlers;
    }
}
