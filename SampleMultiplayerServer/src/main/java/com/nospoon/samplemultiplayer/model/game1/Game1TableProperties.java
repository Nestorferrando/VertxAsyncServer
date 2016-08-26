package com.nospoon.samplemultiplayer.model.game1;

import com.nospoon.samplemultiplayer.handlers.game1.Game1lHandler;
import com.nospoon.samplemultiplayer.model.common.Range;
import com.nospoon.samplemultiplayer.model.common.room.TableProperties;

import java.util.function.Consumer;

/**
 * Created by Nestor on 8/16/2016.
 */
public class Game1TableProperties extends TableProperties<Game1lHandler,Game1Config, Game1TableSelections> {

    private static final int MAX_PLAYERS = 4;

    private Class<Game1lHandler> handlerClass;
    private Range difficulty;
    private Range credits;


    public Game1TableProperties(Range difficulty, Range credits) {
        super(MAX_PLAYERS,new Game1TableSelections(credits.getMin(),difficulty.getMin()), Game1TableSelections.class);
        this.difficulty = difficulty;
        this.credits = credits;
        this.handlerClass=Game1lHandler.class;
    }

    public Range getDifficulty() {
        return difficulty;
    }

    public Range getCredits() {
        return credits;
    }


    @Override
    public Class getGameType() {
        return handlerClass;
    }

    @Override
    public Game1Config getGameConfig(Consumer<Void> gameFinishedConsumer,int currenPlayers) {
        return new Game1Config(currenPlayers,selections.getSelectedDifficulty(),selections.getSelectedCredits(),gameFinishedConsumer);
    }
}
