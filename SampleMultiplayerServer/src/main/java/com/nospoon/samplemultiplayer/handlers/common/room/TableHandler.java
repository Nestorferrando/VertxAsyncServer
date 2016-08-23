package com.nospoon.samplemultiplayer.handlers.common.room;

import com.nospoon.samplemultiplayer.handlers.MultiplayerHandler;
import com.nospoon.samplemultiplayer.model.common.room.TableConfig;
import com.nospoon.samplemultiplayer.model.common.room.TableProperties;
import com.nospoon.vertxserver.core.messagehandlers.HandlerConsumers;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by Nestor on 8/13/2016.
 */
public class TableHandler<T extends TableProperties> extends MultiplayerHandler<TableConfig<T>> {


    private List<Consumer<TableHandler>> onTableFinished  = new ArrayList<>();

    public String getID()
    {
        return config().getTableID();
    }

    @Override
    protected HandlerConsumers createAttachmentConsumers() {
        return null;
    }

    @Override
    protected void onStart() {

    }
}
