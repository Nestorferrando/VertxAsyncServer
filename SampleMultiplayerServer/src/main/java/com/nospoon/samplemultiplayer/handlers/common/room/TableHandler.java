package com.nospoon.samplemultiplayer.handlers.common.room;

import com.nospoon.samplemultiplayer.handlers.MultiplayerHandler;
import com.nospoon.samplemultiplayer.model.common.room.TableProperties;
import com.nospoon.vertxserver.core.messagehandlers.HandlerConsumers;

/**
 * Created by Nestor on 8/13/2016.
 */
public class TableHandler<T extends TableProperties> extends MultiplayerHandler<T> {

    @Override
    protected HandlerConsumers createAttachmentConsumers() {
        return null;
    }

    @Override
    protected void onStart() {

    }
}
