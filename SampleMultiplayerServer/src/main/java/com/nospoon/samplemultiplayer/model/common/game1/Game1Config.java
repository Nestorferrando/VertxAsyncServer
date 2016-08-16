package com.nospoon.samplemultiplayer.model.common.game1;

/**
 * Created by Nestor on 8/12/2016.
 */
public class Game1Config {

    private int difficulty;
    private int credits;

    public Game1Config(int difficulty, int credits) {
        this.difficulty = difficulty;
        this.credits = credits;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public int getCredits() {
        return credits;
    }
}
