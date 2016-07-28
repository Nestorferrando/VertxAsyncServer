package com.nospoon.vertxserver.core.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nestor on 7/26/2016.
 */
public class Player {

    public String session;
    public Map<String, String> playerData;

    public Player(String session) {
        this.session = session;
        playerData = new HashMap<>();
    }

    public String getSession() {
        return session;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        return session != null ? session.equals(player.session) : player.session == null;

    }

    @Override
    public int hashCode() {
        return session != null ? session.hashCode() : 0;
    }
}
