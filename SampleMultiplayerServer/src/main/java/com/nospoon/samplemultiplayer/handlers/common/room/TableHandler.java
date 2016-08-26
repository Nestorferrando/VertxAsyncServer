package com.nospoon.samplemultiplayer.handlers.common.room;

import com.nospoon.samplemultiplayer.handlers.MultiplayerHandler;
import com.nospoon.samplemultiplayer.messages.fromserver.table.GameStarted;
import com.nospoon.samplemultiplayer.messages.fromserver.table.TableClosed;
import com.nospoon.samplemultiplayer.model.common.room.TableConfig;
import com.nospoon.samplemultiplayer.model.common.room.TableProperties;
import com.nospoon.samplemultiplayer.model.common.room.TableState;
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


    private List<Consumer<TableHandler>> onTableFinished = new ArrayList<>();
    private List<Player> players = new ArrayList<>();
    private TableState state = TableState.OPEN;

    public String getID() {
        return config().getTableID();
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public boolean hasPlayer(Player player) {
        return players.contains(player);
    }

    public TableConfig<T> getConfig() {
        return config();
    }

    public TableState getState() {
        return state;
    }

    @Override
    protected HandlerConsumers createAttachmentConsumers() {

        return new HandlerConsumers(player -> {
            players.add(player);
            if (players.size() == config().getTableProperties().getSize()) startGame();
        }, player -> {

            if (isMaster(player)) {
                closeTable();
            } else {
                players.remove(player);
            }

        });
    }

    private void closeTable() {
        send().toPlayers(players, new TableClosed());
        getAttacher().detachPlayers(players);
    }

    private void startGame() {
        state = TableState.PLAYING;
        send().toPlayers(players, new GameStarted());

        handlerManager()
                .createHandler(config().getTableProperties().getGameType(), config().getTableProperties().getGameConfig(onGameFinished()))
                .getAttacher().attachPlayers(players);


    }

    private Consumer<Void> onGameFinished() {
        return aVoid -> {

        };
    }

    private boolean isMaster(Player player) {
        return player.equals(players.get(0));
    }

    @Override
    protected void onStart() {

    }
}
