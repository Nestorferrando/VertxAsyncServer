package com.nospoon.samplemultiplayer.model.game1;

/**
 * Created by Nestor on 8/16/2016.
 */
public class Game1TableSelections {

    private int selectedCredits;
    private int selectedDifficulty;

    public Game1TableSelections(int selectedCredits, int selectedDifficulty) {
        this.selectedCredits = selectedCredits;
        this.selectedDifficulty = selectedDifficulty;
    }

    public int getSelectedCredits() {
        return selectedCredits;
    }

    public int getSelectedDifficulty() {
        return selectedDifficulty;
    }
}
