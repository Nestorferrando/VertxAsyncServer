package com.nospoon.samplemultiplayer.handlers.game1;

import com.nospoon.samplemultiplayer.handlers.MultiplayerHandler;
import com.nospoon.samplemultiplayer.messages.fromserver.games.game1.Game1Finished;
import com.nospoon.samplemultiplayer.messages.fromserver.games.game1.YouLost;
import com.nospoon.samplemultiplayer.messages.fromserver.games.game1.YouWon;
import com.nospoon.samplemultiplayer.model.game1.Game1Config;
import com.nospoon.vertxserver.core.messagehandlers.HandlerConsumers;
import com.nospoon.vertxserver.core.model.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Nestor on 8/2/2016.
 */
public class Game1lHandler extends MultiplayerHandler<Game1Config> {


    private List<Player> playingPlayers = new ArrayList<>();


    @Override
    protected HandlerConsumers createAttachmentConsumers() {

        return new HandlerConsumers(player -> {
            playingPlayers.add(player);
            if (playingPlayers.size()==config().getPlayersAmount())
            {
                startGame();
            }

        },player->{
            playingPlayers.remove(player);
        });
    }

    private void startGame() {

        //chooseRandomPlayer
        int random = new Random().nextInt(playingPlayers.size());
        Player winner = playingPlayers.get(random);
        send().toPlayer(winner, new YouWon());
        send().toPlayersBut(playingPlayers,winner, new YouLost());
        send().toPlayers(playingPlayers, new Game1Finished());
        getAttacher().detachPlayers(playingPlayers);
    }

    @Override
    public void onStart() {

    }
}
