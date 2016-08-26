package com.nospoon.samplemultiplayer.model.common;

/**
 * Created by Nestor on 8/16/2016.
 */
public class Range {

    private int min;
    private int max;

    public Range(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public boolean isInside(int value)
    {
        return value>=min  && value<=max;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }
}
