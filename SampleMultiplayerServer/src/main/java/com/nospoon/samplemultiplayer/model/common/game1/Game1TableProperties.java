package com.nospoon.samplemultiplayer.model.common.game1;

import com.nospoon.samplemultiplayer.handlers.game1.Game1lHandler;
import com.nospoon.samplemultiplayer.model.common.Range;
import com.nospoon.samplemultiplayer.model.common.room.TableProperties;

/**
 * Created by Nestor on 8/16/2016.
 */
public class Game1TableProperties implements TableProperties<Game1lHandler,Game1Config, Game1TableSelections> {

    private Class<Game1lHandler> handlerClass;
    private Range<Integer> difficulty;
    private Range<Integer> credits;



    public Game1TableProperties(Range<Integer> difficulty, Range<Integer> credits) {
        this.difficulty = difficulty;
        this.credits = credits;
        this.handlerClass=Game1lHandler.class;
    }

    public Range<Integer> getDifficulty() {
        return difficulty;
    }

    public Range<Integer> getCredits() {
        return credits;
    }




    @Override
    public Class getGameType() {
        return handlerClass;
    }

    @Override
    public Game1Config getGameConfig(Game1TableSelections selections) {
        return new Game1Config(selections.getSelectedDifficulty(),selections.getSelectedCredits());
    }
}