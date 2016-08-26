package com.nospoon.samplemultiplayer.model.game1;

import java.util.function.Consumer;

/**
 * Created by Nestor on 8/12/2016.
 */
public class Game1Config {

    private int playersAmount;
    private int difficulty;
    private int credits;
    private Consumer<Void> gameFinishedConsumer;

    public Game1Config(int playersAmount,int difficulty, int credits, Consumer<Void> gameFinishedConsumer) {
        this.playersAmount = playersAmount;
        this.difficulty = difficulty;
        this.credits = credits;
        this.gameFinishedConsumer = gameFinishedConsumer;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public int getCredits() {
        return credits;
    }

    public Consumer<Void> getGameFinishedConsumer() {
        return gameFinishedConsumer;
    }

    public int getPlayersAmount() {
        return playersAmount;
    }
}
