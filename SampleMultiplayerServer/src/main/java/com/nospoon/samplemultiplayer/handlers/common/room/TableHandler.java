package com.nospoon.samplemultiplayer.handlers.common.room;

import com.nospoon.samplemultiplayer.handlers.MultiplayerHandler;
import com.nospoon.samplemultiplayer.model.common.room.TableConfig;
import com.nospoon.samplemultiplayer.model.common.room.TableProperties;
import com.nospoon.vertxserver.core.messagehandlers.HandlerConsumers;
import com.nospoon.vertxserver.core.model.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by Nestor on 8/13/2016.
 */
public class TableHandler<T extends TableProperties> extends MultiplayerHandler<TableConfig<T>> {


    private List<Consumer<TableHandler>> onTableFinished  = new ArrayList<>();
    private List<Player> players = new ArrayList<>();

    public String getID()
    {
        return config().getTableID();
    }


    public List<Player> getPlayers()
    {
        return Collections.unmodifiableList(players);
    }

    public boolean hasPlayer(Player player)
    {
        return players.contains(player);
    }


    public TableConfig<T> getConfig()
    {
        return config();
    }

    @Override
    protected HandlerConsumers createAttachmentConsumers() {
        return null;
    }

    @Override
    protected void onStart() {

    }
}
