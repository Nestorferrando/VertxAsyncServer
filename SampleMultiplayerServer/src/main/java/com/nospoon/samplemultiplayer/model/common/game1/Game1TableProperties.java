package com.nospoon.samplemultiplayer.model.common.game1;

import com.nospoon.samplemultiplayer.handlers.game1.Game1lHandler;
import com.nospoon.samplemultiplayer.model.common.Range;
import com.nospoon.samplemultiplayer.model.common.room.TableProperties;

/**
 * Created by Nestor on 8/16/2016.
 */
public class Game1TableProperties implements TableProperties<Game1lHandler,Game1Config> {

    private Class<Game1lHandler> handlerClass;
    private Range<Integer> difficulty;
    private Range<Integer> credits;
    private int selectedCredits;
    private int selectedDifficulty;


    public Game1TableProperties(Range<Integer> difficulty, Range<Integer> credits) {
        this.difficulty = difficulty;
        this.credits = credits;
        this.handlerClass=Game1lHandler.class;
        this.selectedDifficulty = difficulty.getMin();
        this.selectedCredits = credits.getMin();
    }

    public Range<Integer> getDifficulty() {
        return difficulty;
    }

    public Range<Integer> getCredits() {
        return credits;
    }

    public int getSelectedCredits() {
        return selectedCredits;
    }

    public int getSelectedDifficulty() {
        return selectedDifficulty;
    }

    public void setSelectedCredits(int selectedCredits) {
        this.selectedCredits = selectedCredits;
    }

    public void setSelectedDifficulty(int selectedDifficulty) {
        this.selectedDifficulty = selectedDifficulty;
    }


    public Game1Config getConfigFromSelectedParams()
    {
        return new Game1Config(selectedCredits,selectedDifficulty);
    }

    @Override
    public Class getGameType() {
        return handlerClass;
    }

    @Override
    public Game1Config getGameConfig() {
        return new Game1Config(selectedDifficulty,selectedCredits);
    }
}
