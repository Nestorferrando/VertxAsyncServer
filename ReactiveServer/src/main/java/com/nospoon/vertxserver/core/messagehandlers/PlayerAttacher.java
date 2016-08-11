package com.nospoon.vertxserver.core.messagehandlers;

import com.nospoon.vertxserver.core.model.Player;

import java.util.List;

/**
 * Created by Nestor on 8/11/2016.
 */
public interface PlayerAttacher {


void attachPlayers(List<Player> players);

void detachPlayers(List<Player> players);

void attachPlayer(Player player);

void detachPlayer(Player player);

}
