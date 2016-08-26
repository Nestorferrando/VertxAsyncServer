package com.nospoon.samplemultiplayer.handlers.common.room;

import com.nospoon.jpromises.Promise;
import com.nospoon.jpromises.Promises;
import com.nospoon.samplemultiplayer.handlers.MultiplayerHandler;
import com.nospoon.samplemultiplayer.messages.fromclient.room.CreateTableRequest;
import com.nospoon.samplemultiplayer.messages.fromclient.room.GetTablePropertiesRequest;
import com.nospoon.samplemultiplayer.messages.fromclient.room.GetTablesRequest;
import com.nospoon.samplemultiplayer.messages.fromclient.room.JoinTableRequest;
import com.nospoon.samplemultiplayer.messages.fromserver.room.CreateTableResponse;
import com.nospoon.samplemultiplayer.messages.fromserver.room.GetTablePropertiesResponse;
import com.nospoon.samplemultiplayer.messages.fromserver.room.GetTablesResponse;
import com.nospoon.samplemultiplayer.messages.fromserver.room.JoinTableResponse;
import com.nospoon.samplemultiplayer.model.common.room.RoomProperties;
import com.nospoon.samplemultiplayer.model.common.room.TableConfig;
import com.nospoon.samplemultiplayer.model.common.room.TableProperties;
import com.nospoon.samplemultiplayer.model.common.room.TableState;
import com.nospoon.vertxserver.core.messagehandlers.HandlerConsumers;
import com.nospoon.vertxserver.core.model.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Nestor on 8/2/2016.
 */
public class RoomHandler<Q extends TableProperties> extends MultiplayerHandler<RoomProperties<Q>> {


    private List<Player> playersInRoom = new ArrayList<>();
    private List<TableHandler<Q>> tables = new ArrayList<>();
    private int tableCounter;


    public Promise<Void> on(GetTablesRequest request, Player player) {
        List<String> tableIDs = new ArrayList<>();
        List<String> tableSelections = new ArrayList<>();
        List<List<String>> tablePlayers = new ArrayList<>();

        tables.forEach(table -> {
            tableIDs.add(table.getID());
            tableSelections.add(table.getConfig().getTableProperties().getSerializedSelection());
            tablePlayers.add(table.getPlayers().stream().map(tableplayer -> dbApi().getMultiplayerID(tableplayer.getSession())).collect(Collectors.toList()));

        });
        send().toPlayer(player, new GetTablesResponse(tableIDs, tableSelections, tablePlayers));
        return Promises.resolve();
    }

    public Promise<Void> on(GetTablePropertiesRequest request, Player player) {

        send().toPlayer(player, new GetTablePropertiesResponse(config().getTableProperties().getSerialized()));
        return Promises.resolve();
    }


    public Promise<Void> on(CreateTableRequest request,Player player) {
        if (tables.stream().anyMatch(table->table.hasPlayer(player)))
        {
            send().toPlayer(player, new CreateTableResponse(false,""));
        }
        else
        {
            String tableID=createTableForPlayer(player);
            send().toPlayer(player, new CreateTableResponse(true,tableID));
        }
        return Promises.resolve();
    }

    public Promise<Void> on(JoinTableRequest request, Player player) {

        Optional<TableHandler<Q>> table = tables.stream().filter(tab->tab.getID().equals(request.getID()) && !tab.hasPlayer(player) && tab.getState()== TableState.OPEN).findAny();

        if (table.isPresent())
        {
            table.get().getAttacher().attachPlayer(player);
        }
       send().toPlayer(player,new JoinTableResponse(table.isPresent()));

        return Promises.resolve();
    }



    private String createTableForPlayer(Player master) {

        TableHandler<Q> table = handlerManager().createHandler(TableHandler.class, new TableConfig<Q>(config().TableProperties, "table" + tableCounter));
        tables.add(table);
        table.getAttacher().attachPlayer(master);
        tableCounter++;
        return table.getID();
    }


    @Override
    protected HandlerConsumers createAttachmentConsumers() {
        return new HandlerConsumers(player -> playersInRoom.add(player), player -> playersInRoom.remove(player));
    }

    public boolean isRoomFull() {
        return playersInRoom.size() >= config().maxPlayers;
    }

    public int getPlayersCount() {
        return playersInRoom.size();
    }

    public int getMaxPlayers() {
        return config().getMaxPlayers();
    }

    public String getID() {
        return config().roomID;
    }

    @Override
    public void onStart() {

    }
}
